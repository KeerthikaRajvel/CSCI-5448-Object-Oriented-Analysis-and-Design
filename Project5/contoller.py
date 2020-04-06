from collections import namedtuple
from flask import Flask, render_template

app = Flask(__name__)


class Controller:
    def hello(self):
        return render_template('index.html')


controller = Controller()
app.add_url_rule('/', 'index', lambda: controller.hello())
app.run(debug = True,port=3400)