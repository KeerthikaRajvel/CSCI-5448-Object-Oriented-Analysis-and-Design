from collections import namedtuple
from flask import Flask, render_template

app = Flask(__name__)


class Controller:
    instance=None
    @staticmethod
    def getInstance():
        """ Static access method. """
        if Controller.instance == None:
            Controller()
        return Controller.instance

    def __init__(self):
        """ Virtually private constructor. """
        if Controller.instance != None:
            raise Exception("This class is a singleton!")
        else:
            Controller.instance = self

    def hello(self):
        return render_template('index.html')
    def dashboard(self):
        return render_template('dashboard.html')
    def signup(self):
        return render_template('signup.html')
    def display_questionnaire(self,questionnaire): #Strategy Pattern
        return questionnaire
    def fillRoom(self):
        return render_template('fillRoom.html')

def findRoomQuestionnaire():
    return render_template('findRoomQuestionnaire.html')
def fillRoomQuestionnaire():
    return render_template('fillRoomQuestionnaire.html')

controller1 = Controller()
controller2 = Controller.getInstance() #Singleton Pattern
app.add_url_rule('/', 'index', lambda: controller2.hello())
app.add_url_rule('/dashboard','dashboard', lambda: controller2.dashboard(),methods=['POST'])
app.add_url_rule('/signup','signup', lambda: controller2.signup())
app.add_url_rule('/fillRoom','fillRoom', lambda: controller2.fillRoom())
app.add_url_rule('/findRoom','findRoom', lambda: controller2.display_questionnaire(findRoomQuestionnaire()))
app.add_url_rule('/questionnaire','questionnaire', lambda: controller2.display_questionnaire(fillRoomQuestionnaire()),methods=['POST'])
app.run(debug = True,port=3400)