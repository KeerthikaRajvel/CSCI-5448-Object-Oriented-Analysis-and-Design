from flask import Flask, render_template, request

class Controller(object):
    def __init__(self):
        self.app=Flask(__name__)
        self.port=3400
        @self.app.route('/')
        def index():
           return render_template('index.html')

if __name__ == '__main__':
    controller=Controller()
    controller.app.run(debug = True,port=controller.port)