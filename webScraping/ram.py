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
driver.get('https://pcpartpicker.com/products/memory/')
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
    if re.search(brand, 'Silicon', re.IGNORECASE):
        continue

    rtype = item.find(class_='td__spec--1')
    rtypeX = rtype.contents[1].split("-")
    rtype1 = rtypeX[0]
    speed1 = int(rtypeX[1])
    print(rtype1)
    print(speed1)

    module = item.find(class_='td__spec--3')
    module1 = module.contents[1]
    moduleX = module.contents[1].split()
    print("ModuleX "+ str(moduleX))
    moduleQ = int(moduleX[0])
    # find the first matching digits, take the first array hence [0], convert it to integer
    moduleSize = int(re.findall('\d+', moduleX[2])[0])
    print(moduleQ)
    print(moduleSize)

    capacity1 =  moduleQ * moduleSize
    print("Capacity is :" + str(capacity1))
    # print(capacity1)
    
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

    # cursor = db.cursor()
    # sql1 = "insert into components (brand, model, price, image, type) VALUES (%s,%s,%s,%s,%s)"
    # param1 = (brand, model, price1, img1, "RAM")
    # sql2 = "insert into ram (id, capacity, ram_type, speed, module, color) VALUES(%s,%s,%s,%s,%s,%s)"
    # try:
    #     cursor.execute(sql1, param1)
    #     last_id = cursor.lastrowid
    #     param2 = (last_id, capacity1, rtype1, speed1, module1, color1)
    #     cursor.execute(sql2, param2)
    #     db.commit()
    # except MySQLdb.Error as err:
    #     print(err)