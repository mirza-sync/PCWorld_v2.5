# import libraries
import MySQLdb
import requests
from selenium import webdriver
from bs4 import BeautifulSoup
import time
import re

HOST = "localhost"
USERNAME = "root"
PASSWORD = ""
DATABASE = "pcworld"

# Open database dbection
db = MySQLdb.connect(HOST, USERNAME, PASSWORD, DATABASE)

# initialize Selenium to work with Google Chrome
driver = webdriver.Chrome('./venv/Lib/site-packages/chromedriver.exe')
#url to be parsed
driver.get('https://pcpartpicker.com/products/cpu/#k=30')
# wait 1 second for web page to load before start parsing
time.sleep(1)
print('start')
page_source = driver.page_source

#parse the data
soup = BeautifulSoup(page_source, "html.parser")

# items = soup.find_all(class_=re.compile("td__spec--[1-6]"))
items = soup.find_all(class_="tr__product")
for item in items:
    img = item.find(class_='td__image')
    img1 = img.img['src']
    print(img1)
    
    name = item.find(class_='td__nameWrapper')
    name1 = name.p.text
    brand, *name2 = name1.split(" ",1)
    model = " ".join(name2)
    print("Brand : " + brand)
    print("Model : " + model)

    core = item.find(class_='td__spec--1')
    core1 = int(core.contents[1])
    print(core1)
    
    base_clock = item.find(class_='td__spec--2')
    base_clockX = base_clock.contents[1].split()
    base_clock1 = float(base_clockX[0])
    print(base_clock1)

    try:
        max_clock = item.find(class_='td__spec--3')
        max_clockX = max_clock.contents[1].split()
        max_clock1 = float(max_clockX[0])
        print(max_clock1)
    except:
        continue
    
    watt = item.find(class_='td__spec--4')
    wattX = watt.contents[1].split()
    watt1 = int(wattX[0])
    print(watt1)
    
    multi = item.find(class_='td__spec--6')
    multiX = multi.contents[1]
    if multiX == 'Yes':
        multi1 = 1
    else:
        multi1 = 0
    print(multi1)

    price = item.find(class_='td__price')
    pricetemp = price.find(text=re.compile("^\$"))
    if pricetemp==None:
        continue
    price1 = float(pricetemp.strip('$'))*4
    print(price1)

    cursor = db.cursor()
    sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    param1 = (brand, model, price1, img1, "CPU")
    sql2 = "insert into cpu (id, socket, base_clock, max_clock, num_core, multithread, wattage) VALUES(%s,%s,%s,%s,%s,%s,%s)"
    
    try:
        cursor.execute(sql1, param1)
        last_id = cursor.lastrowid
        param2 = (last_id, "LGA1151", base_clock1, max_clock1, core1, multi1, watt1)
        cursor.execute(sql2, param2)
        db.commit()
    except MySQLdb.Error as err:
        print(err)