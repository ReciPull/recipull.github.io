
# importing the requests library 
import requests 

def testget(geturl,val): 
    # defining a params dict for the parameters to be sent to the API 
    PARAMS = {'ID':val} 
    
    # sending get request and saving the response as response object 
    r = requests.get(url = geturl, params = PARAMS) 
    
    # extracting data in json format 
    data = r.json() 
    print('GET returned: {}'.format(data))
    #check the HTTP status code
    print('GET status: {}'.format(r.status_code))
    
def testpost(posturl,val1,val2): 
    # defining a params dict for the parameters to be sent to the API 
    PARAMS = {'name':val1, 'surName':val2} 
    
    # sending get request and saving the response as response object 
    r = requests.post(url = posturl, params = PARAMS) 
    #nothing is returned, just check the HTTP status code
    print('POST status: {}'.format(r.status_code))
    

def main():
    url = 'http://localhost:8280/test/GetNameAlexa'
    testget(url,'foo')
    #url = 'http://localhost:8180/test/SaveName'
    #testpost(url,'name1','name2')

if __name__ == '__main__':
  main()