#-*- coding: utf-8 -*-
from pylab import *
from matplotlib.font_manager import FontProperties
import matplotlib.pyplot as plt

def plotCircle(AllTicket,OnceTicket):

    mpl.rcParams['font.sans-serif'] = ['SimHei']

# make a square figure and axes
    plt.figure(1, figsize=(6,6))
    plt.ax = plt.axes([0.1, 0.1, 0.8, 0.8])

    fracs = [AllTicket - OnceTicket, OnceTicket]             #每一块占得比例，总和为100
    explode=(0, 0.08)             #离开整体的距离，看效果
    labels =  '深圳通:'+ str(AllTicket - OnceTicket), '单程票:'+ str(OnceTicket)  #对应每一块的标志

    plt.pie(fracs, explode=explode, labels=labels,
                autopct='%1.1f%%', shadow=True, startangle=90, colors = ( "r", "y"))
                                 # startangle是开始的角度，默认为0，从这里开始按逆时针方向依次展开
    plt.title('深圳通与单程票进站对比')   #标题

    plt.show()

def plotSquare(dit):
    nameno = {}
    once = {}
    mpl.rcParams['font.sans-serif'] = ['SimHei']

    font = {'family' : 'sans-serif',
        'color'  : 'darkred',
        'weight' : 'normal',
        'size'   : 4,
        }

    with open ("nameno.csv",'r',encoding='gbk') as f:
        for line in f.readlines():
            L = line.split(",")
            nameno[L[0]] = L[1]
    for key,value in dit.items():
        L = key.split("-")
        once[nameno[L[0]]+"-"+nameno[L[1]]] = value

    xsize = len(once)

    xticks = once.keys()#每个柱的下标说明
    gradeGroup = once#用于画图的频率数据

    # xticks = ['A', 'B', 'C', 'D', 'E']#每个柱的下标说明
    # gradeGroup = {'A':200,'B':250,'C':330,'D':400,'E':500}#用于画图的频率数据

    #创建柱状图
    #第一个参数为柱的横坐标
    #第二个参数为柱的高度
    #参数align为柱的对齐方式，以第一个参数为参考标准
    #plt.bar(range(xsize), [gradeGroup.get(xtick, 0) for xtick in xticks], align='center',yerr=0.00001)
    plt.bar(range(xsize), [gradeGroup.get(xtick, 0) for xtick in xticks])

    #设置柱的文字说明
    #第一个参数为文字说明的横坐标
    #第二个参数为文字说明的内容
    plt.xticks(range(xsize), xticks,fontsize=5)

    #设置横坐标的文字说明
    plt.xlabel('OD')
    #设置纵坐标的文字说明
    plt.ylabel('出行人数')
    #设置标题
    plt.title('单程票出行OD比对')
    plt.savefig('latex.png', dpi=1000)
    #绘图
    # plt.show()

def plotZ(allT,onceT):
    mpl.rcParams['font.sans-serif'] = ['SimHei']

    x1 = range(0,24)
    y1 = [allT.get(str(x)) for x in x1]
    print(y1)
    x2 = x1
    y2 = [onceT.get(str(x)) for x in x1]
    print(y2)
    plt.plot(x1,y1,'r',label = "总进站量")
    plt.plot(x2,y2,'g', label = '单程票量')
    plt.legend(loc='upper left')
    plt.title('每时段单程票进站数与总进站数对比') #指定字体
    plt.xlabel('人数')
    plt.ylabel('时段')  #指定大小
    plt.show()


if __name__ == '__main__':
    # plotCircle(1000,102)
    # plotSquare({"1260024000-1261003000":123})
    plotZ({},{})
