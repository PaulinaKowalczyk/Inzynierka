from hello import HelloService


class HelloServicePython(HelloService):
    def __init__(self):
        self.value = "Hello from python"

    def get_result(self):
        l = [
              {
                "id": 1,
                "name": "vader",
                "positive": "0.25",
                "negative": "0.2",
                "neutral": "0",
                "overall": "0.225"
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
        return l
