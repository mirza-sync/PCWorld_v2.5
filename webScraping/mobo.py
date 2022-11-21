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
driver.get('https://pcpartpicker.com/products/motherboard/')
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
    print("Brand : " + brand)
    print("Model : " + model)

    socket = item.find(class_='td__spec--1')
    socket1 = socket.contents[1]
    print(socket1)
    
    form = item.find(class_='td__spec--2')
    form1 = form.contents[1]
    print(form1)

    mem = item.find(class_='td__spec--3')
    memX = mem.contents[1].split()
    mem1 = int(memX[0])
    print(mem1)

    # try:
    #     core_clock = item.find(class_='td__spec--3')
    #     core_clockX = core_clock.contents[1].split()
    #     core_clock1 = int(core_clockX[0])
    #     print(core_clock1)
    # except:
    #     continue
    
    memslot = item.find(class_='td__spec--4')
    memslot1 = int(memslot.contents[1])
    print(memslot1)
    
    try:
        color = item.find(class_='td__spec--5')
        color1 = color.contents[1]
        print(color1)
    except:
        continue

    price = item.find(class_='td__price')
    pricetemp = price.find(text=re.compile("^\$"))
    if pricetemp==None:
        continue
    price1 = float(pricetemp.strip('$'))*4
    print(price1)

    cursor = db.cursor()
    sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    param1 = (brand, model, price1, img1, "Motherboard")
    sql2 = "insert into motherboard (id, formfactor, socket, memory_slot, max_memory, color) VALUES(%s,%s,%s,%s,%s,%s)"
    try:
        cursor.execute(sql1, param1)
        last_id = cursor.lastrowid
        param2 = (last_id, form1, socket1, memslot1, mem1, color1)
        cursor.execute(sql2, param2)
        db.commit()
    except MySQLdb.Error as err:
        print("Error : ".format(err))