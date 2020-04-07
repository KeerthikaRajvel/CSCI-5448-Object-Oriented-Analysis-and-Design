from collections import namedtuple
from flask import Flask, render_template

app = Flask(__name__)


class Controller:
    def hello(self):
        return render_template('index.html')
    def dashboard(self):
        return render_template('dashboard.html')
    def signup(self):
        return render_template('signup.html')

controller = Controller()
app.add_url_rule('/', 'index', lambda: controller.hello())
app.add_url_rule('/dashboard','dashboard', lambda: controller.dashboard(),methods=['POST'])
app.add_url_rule('/signup','signup', lambda: controller.signup())
app.run(debug = True,port=3400)