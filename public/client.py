
# importing the requests library 
import requests 
import json

def testget(geturl,val): 
    # defining a params dict for the parameters to be sent to the API 
    PARAMS = {'ID':val} 
    #print("Params ", PARAMS)
    
    # sending get request and saving the response as response object 
    r = requests.get(url = geturl, params = PARAMS) 
    
    # extracting data in json format 
    data = r.json()
    #print(data[1]) 
    #print('GET returned: {}'.format(data))
    #print("final val ", data["`French Toast`https://food52.com/recipes/4611-bell-less-whistle-less-damn-good-french-toast?utm_source=cj&affil=cj&utm_medium=affiliate&utm_campaign=Food52+Shop&company=Skimlinks&website=8248019&cjevent=3c1144c467a911e9823300a90a1c0e0d`Scrambled Eggs`https://food52.com/recipes/6739-soft-scrambled-eggs?utm_source=cj&affil=cj&utm_medium=affiliate&utm_campaign=Food52+Shop&company=Skimlinks&website=8248019&cjevent=3d2d268b67a911e9814100a50a1c0e12`Brownies`https://www.browneyedbaker.com/chewy-brownies/`Chocolate Chip Cookies`http://everydayannie.com/2008/09/08/thick-and-chewy-chocolate-chip-cookies/"])
    #check the HTTP status code
    #print('GET status: {}'.format(r.status_code))
    for key in data:
      k = key
      #print(k)
    #print(k)

    #evens are names, odds are URLs

    newFin = k.split("`")
    evenIt = 0
    oddIt = 1
    newDict = {}
    #print("newFin: ", newFin)
    #print("len: ", len(newFin)/2)
    
    for i in range(int(len(newFin)/2)):
      x = {newFin[evenIt]:newFin[oddIt]}
      newDict.update(x)
      evenIt+=2
      oddIt+=2

    newData = json.dumps(newDict)
    #print(newData)
    return newData
    
def testpost(posturl,val1,val2): 
    # defining a params dict for the parameters to be sent to the API 
    PARAMS = {'name':val1, 'surName':val2} 
    
    # sending get request and saving the response as response object 
    r = requests.post(url = posturl, params = PARAMS) 
    #nothing is returned, just check the HTTP status code
    print('POST status: {}'.format(r.status_code))
    

def main():
    url = 'http://localhost:8280/test/GetNameAlexa'
    testget(url,'egg')
    #url = 'http://localhost:8180/test/SaveName'
    #testpost(url,'name1','name2')

if __name__ == '__main__':
  main()