from hello import HelloService
# from system import modules
# modules.clear()
from polish_vader_sentiment import SentimentIntensityAnalyzer


# print(path)
# ['/home/kamil/.m2/repository/org/python/jython-slim/2.7.2b2/Lib',
#  '/home/kamil/.m2/repository/org/python/jython-slim/2.7.2b2/jython-slim-2.7.2b2.jar/Lib', '__classpath__', '__pyclasspath__/', '/Users/macbook/jython2.7.1/Lib']





class HelloServicePython(HelloService):
    def __init__(self):
        #print "Python started"

    def get_result(self, text):
        #print text
        text = text.encode('utf-8')
        analyzer = SentimentIntensityAnalyzer()
        #print "Analyzer started"
        vs = analyzer.polarity_scores(text)
        #print "Polarity scores counted"
        list = [
              {
                "id": 1,
                "name": "vader",
                "positive": vs["pos"],
                "negative": vs["neg"],
                "neutral": vs["neu"],
                "overall": vs["compound"]
              },
              {
                "id": 2,
                "name": "slowosiec",
                "positive": "0.25",
                "negative": "0.2",
                "neutral": "0",
                "overall": "0.225"
              }
            ]
        return list
