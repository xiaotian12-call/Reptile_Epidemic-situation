import requests
import json
from pymysql import *
import requests
from retrying import retry
headers = {"User-Agent": "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Mobile Safari/537.36"
            ,"Referer": "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1"}


def _parse_url(url):
    response = requests.get(url,headers=headers,timeout=3) #3秒之后返回
    return response.content.decode()


def parse_url(url):
    try:
        html_str = _parse_url(url)
    except:
        html_str = None
    return html_str


class yiqing:

    url="https://c.m.163.com/ug/api/wuhan/app/data/list-total?t=316765429316"

    def getContent_list(self,html_str):
        dict_data = json.loads(html_str)
        #各省的数据
        content_list = dict_data["data"]
        
        return content_list

    
    def saveContent_list(self,i):
        # 打开数据库连接（ip/数据库用户名/登录密码/数据库名）
        con = connect("localhost", "root", "root", "text")
        # 使用 cursor() 方法创建一个游标对象 cursor
        cursors = con.cursor()
        # 使用 execute()  方法执行 SQL 查询 返回的是你影响的行数
        
        row = cursors.execute("insert into provinces values(%s,%s,%s,%s,%s,%s,%s,%s)",
                              (i.get('id'),i.get('name'),i.get('total').get('confirm'),
                               i.get('total').get('suspect'),i.get('total').get('heal'),
                               i.get('total').get('dead'),i.get('total').get('severe'),
                               i.get('lastUpdateTime')))
        for j in i.get('children'):
            row = cursors.execute("insert into citys values(%s,%s,%s,%s,%s,%s,%s,%s)",
                              (j.get('id'),j.get('name'),j.get('total').get('confirm'),
                               j.get('total').get('suspect'),j.get('total').get('heal'),
                               j.get('total').get('dead'),j.get('total').get('severe'),
                               j.get('lastUpdateTime')))
        con.commit()#提交事务
        con.close()# 关闭数据库连接

        
    def run(self): #实现主要逻辑
        #请求数据
        html_str = parse_url(self.url)
        #获取数据
        content_list = self.getContent_list(html_str)
        values = content_list["areaTree"][0]["children"]
        for i in values:
            self.saveContent_list(i)

if __name__ == '__main__':
    yq = yiqing()
    yq.run()
    print('爬取，存储成功！！')
