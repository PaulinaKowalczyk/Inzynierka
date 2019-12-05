# Polish Sentiment Analysis

Simple web app to perform sentiment analysis for polish text

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You have to have Angular and mysql installed 

and you have to set up the database:
```
mysql> create database db_example; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```
db_example is the name of the db and it has to match with the one in application.properties
the same goes for springuser and password.

To run the project you have to run the angular CLI (if imported into Inteliij you can just press run) then run the java application.

For sentiment analysis to work correctly you have to run the tagger through docker:
```
sudo docker run -it -p 9200:9200 apohllo/krnnt:0.1 python3 /home/krnnt/krnnt/krnnt_serve.py /home/krnnt/krnnt/data
```

additionaly you have to copy the contents of directory "Python Sentiment Analysis"
to the directory is the path from jython (just uncomment line print(path) in HelloServicePython)
exemplary output:
```
>>print(path)
['/home/path/kamil/path/to/jython/jython-slim/2.7.2b2/Lib', '/home/kamil/.m2/repository/org/python/jython-slim/2.7.2b2/jython-slim-2.7.2b2.jar/Lib', '__classpath__', '__pyclasspath__/', '/Users/macbook/jython2.7.1/Lib']
```

so you have to copy the contents into /home/path/kamil/path/to/jython/jython-slim/2.7.2b2/Lib
## Authors

* **Kamil Świerad** - [kswierad](https://github.com/kswierad)
* **Paulina Kowalczyk**
* **Katarzyna Błachut**
* **Jakub Kołoczek**

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
