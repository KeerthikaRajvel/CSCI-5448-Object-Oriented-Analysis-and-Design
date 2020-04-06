from flask import Flask, render_template, request
from flask import jsonify

class Controller(object):
    def __init__(self):
        self.app=Flask(__name__)
        @self.app.route('/')
        def index():
           return render_template('index.html')

if __name__ == '__main__':
    controller=Controller()
    controller.app.run(debug = True,port=3400)