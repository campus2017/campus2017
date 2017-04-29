import os

dic = {}

def save(filepath):
	with open (filepath,'r',encoding='utf-8') as f:
		i = 0
		for line  in f.readlines():
			L = line.split(',')
            dic[L[0]] =
def findallfile(document):
	for filename in os.listdir(document):
		save(os.path.join(document,filename))

if __name__ == '__main__':
    findallfile("data")
