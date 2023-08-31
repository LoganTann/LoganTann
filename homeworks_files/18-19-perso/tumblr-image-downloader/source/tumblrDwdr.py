# coding: utf-8
#doc (this will be displayed in the first run: 
"""
Simple Tumblr Image Downloader by Logan Tann
Version 1.0.2
Under the MIT/X11 License

Changelog for this version :
    -if there is not <figure> balises,search in body (will REALLY download all image)"""
import os
try:
    from bs4 import BeautifulSoup
except ImportError as e:
    print("""Unable to load BeatifulSoup Librairy !\nTry to install it manually,or using pip !!""")
    input("press a key to continue")
    raise ImportError("""Unable to load BeatifulSoup Librairy !\nTry to install it manually,or using pip.
    error : {}""".format(e))
try:
    import urllib.request
except ImportError as e:
    print("""Unable to load urllib !! Are you using python 3 ?""")
    input("press a key to continue")
    raise ImportError("""Unable to load urllib !! Are you using python 3 ?
    error : {}""".format(e))


def download(url,name):
    urllib.request.urlretrieve(url, name)
    return True

def isPageOK(url):
    try:
        test = urllib.request.urlopen(url)
    except urllib.request.HTTPError as e:
        return False
    else:
        return True
    
def job(url):
    jobList = []
    html = urllib.request.urlopen(url).read()
    allFigures = BeautifulSoup(html,"html.parser").find_all('figure')
    if len(allFigures)<=1:
        print("unable to find <figure> balises. It is due to a different theme. I will download from the <body> but I will download ALL images inside the page.")
        allFigures = BeautifulSoup(html,"html.parser").find_all('body')
        
    for figure in allFigures:
        allImg = BeautifulSoup(str(figure),"html.parser").find_all('img')
        for img in allImg:
            jobList.append(img['src'])
    if len(jobList)<=0:
        print("error : i didn't find anything !! it is probably due to a theme that load the content of the page using javascript but i can't execute JS! I think i will download anything !\n")
    return jobList

def main():
    #main loop
    quit = False
    while not quit:
        
        #Set download directory
        print('\n----------\n\nCurrent working directoy :',os.getcwd())
        path = input('Enter a folder to download : ')
        if not os.path.isdir(path):
            os.makedirs(path)
        os.chdir(path)
        print('Current directory set to :',os.getcwd())
        #set tumblr url and download
        inputUrl = input("Enter the tumblr url : ")
        if isPageOK(inputUrl):
            print("Page is OK. Reading  : " + inputUrl)
            jobList = job(inputUrl)
            compteur = 1
            for url in jobList:
                extension = "." + url.split('.')[-1]
                print("Downloading : ",url," to <current folder>/{0}{1}".format(compteur,extension))
                try:
                    download(url,str(compteur) + extension)
                except Exception as e:
                    print("error during downloading "+url+". Ignoring this file...")
                compteur += 1
        else:
            print("An error occured ! Did the url is valid or correct ?")
            
        os.chdir('../')
        
        choix = input("Do you want to download another page ? [y/n]:")
        if choix != "y":
            quit = True
    
    
if __name__ == '__main__':
    print(__doc__)
    main()
    print('Program finished.')
    input('Press a key to exit')
