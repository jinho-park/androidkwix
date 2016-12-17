#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("localhost","root","jinsejin2","Notice")

# prepare a cursor object using cursor() methond
cursor = db.cursor()

# execute SQL query using execute () method
cursor.execute("DROP TABLE IF EXISTS Notice")

# Create table as per requirement
sql = """CREATE TABLE Notice (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Category VARCHAR(50) NOT NULL,
	Title VARCHAR(255),
	Hyperlink VARCHAR(255),
	Date VARCHAR(50))"""

cursor.execute(sql)

#import BeautifulSoup
from bs4 import BeautifulSoup
from urllib.request import Request,urlopen
url = 'http://www.kw.ac.kr/ko/life/notice.do'

#contact webpage
req = Request(url, headers={'User-Agent':'Mozilla/5.0'})
webpage = urlopen(req).read()
urlopen(req).close()

soup = BeautifulSoup(webpage,"lxml")
sample = soup.find_all('li',{'class':'top-notice'},{'class':'top-notice.row-bg'})


sql = """INSERT INTO Notice(Category, Title) VALUES(%s, %s)"""
cntId = 1

for s in sample:
	if s.find_all('span',{'class':'ico-notice'}) != -1:
		for category in s.find_all('a'):
			print((category.text).split('\n\t\t\t\t\t\t\n\t\t\t\t\t\t')[0].replace("\n",""),(category.text).split('\n\t\t\t\t\t\t\n\t\t\t\t\t\t')[1],url+category.get('href'))
			try:			
				cursor.execute("INSERT INTO Notice(Category, Title, Hyperlink) VALUES('%s', '%s', '%s')" % ((category.text).split('\n\t\t\t\t\t\t\n\t\t\t\t\t\t')[0].replace("\n",""),(category.text).split('\n\t\t\t\t\t\t\n\t\t\t\t\t\t')[1],url+category.get('href')))
				db.commit()
			except:
				db.rollback()
		for date in s.find_all('p',{'class':'info'}):
			print(date.text.split('  ')[3])
			try:
				# Execute the SQL command
				cursor.execute("UPDATE Notice SET Date = '%s' WHERE Id = '%s'" % (date.text.split('  ')[3],cntId))
				cntId = cntId + 1
				# Commit your changes in the database
				db.commit()
			except:
				# Rollback in case ther is any error
				db.rollback()
			
#disconnect from server
db.close()
