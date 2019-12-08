from hello import HelloService
# from system import modules
# modules.clear()
from sys import path
from polish_vader_sentiment import SentimentIntensityAnalyzer


print(path)





class HelloServicePython(HelloService):
    def __init__(self):
        print ("w/e")

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
