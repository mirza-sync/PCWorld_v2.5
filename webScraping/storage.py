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

driver = webdriver.Chrome('./venv/Lib/site-packages/chromedriver.exe')
driver.get('https://pcpartpicker.com/products/internal-hard-drive/')
time.sleep(1)
print('start')
page_source = driver.page_source

soup = BeautifulSoup(page_source, "html.parser")

items = soup.find_all(class_="tr__product")
for item in items:
    img = item.find(class_='td__image')
    img1 = img.img['src']
    print(img1)
    
    name = item.find(class_='td__nameWrapper')
    name1 = name.p.text
    brand, *name2 = name1.split(" ",1)
    model = " ".join(name2)
    if re.search(brand, 'Hyundai', re.IGNORECASE):
        continue
    if re.search(brand, 'Silicon', re.IGNORECASE):
        continue
    if re.search(brand, 'SK', re.IGNORECASE):
        continue
    if re.search(brand, 'Western', re.IGNORECASE):
        brand = "WD"
        modelX = " ".join(name2)
        modeltemp, *modelY = modelX.split(" ", 1)
        modelZ = " ".join(modelY)
        model = modelZ.replace("WD", "Caviar")
    print("Brand : " + brand)
    print("Model : " + model)

    capacity = item.find(class_='td__spec--1')
    capacity1 = capacity.contents[1]
    print(capacity1)

    stype = item.find(class_='td__spec--3')
    stype1 = stype.contents[1]
    print(stype1)
    
    form = item.find(class_='td__spec--5')
    form1 = form.contents[1]
    print(form1)

    price = item.find(class_='td__price')
    pricetemp = price.find(text=re.compile("^\$"))
    if pricetemp==None:
        continue
    price1 = float(pricetemp.strip('$'))*4
    print(price1)

    cursor = db.cursor()
    sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    param1 = (brand, model, price1, img1, "Storage")
    sql2 = "insert into storage (id, storage_type, capacity, formfactor) VALUES(%s,%s,%s,%s)"

    try:
        cursor.execute(sql1, param1)
        last_id = cursor.lastrowid
        param2 = (last_id, stype1, capacity1, form1)
        cursor.execute(sql2, param2)
        db.commit()
    except MySQLdb.Error as err:
        print(err)