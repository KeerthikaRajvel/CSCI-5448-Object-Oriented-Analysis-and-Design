from flask import Flask, render_template,request
from model import Model
from signin import Signin
from signup import Signup

app = Flask(__name__)

class Controller:

    instance=None
    model=None
    port=None
    signinObj=None
    signupObj=None

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
            Controller.model = Model()
            Controller.port = 3400
            Controller.signupObj = Signup()
            Controller.signinObj = Signin()

    def add_url_rules(self):
        app.add_url_rule('/', 'index', lambda: controller2.hello())
        app.add_url_rule('/signup', 'signup', lambda: controller2.signup())
        app.add_url_rule('/login', 'login', lambda: controller2.login(),methods=['POST'])
        app.add_url_rule('/questionnaire', 'questionnaire', lambda: controller2.display_questionnaire(),methods=['POST'])
        app.add_url_rule('/findroom_dashboard', 'findroom_dashboard',lambda: controller2.display_dashboard(findroom(controller1.model)), methods=['POST'])
        app.add_url_rule('/fillroom_dashboard', 'fillroom_dashboard',lambda: controller2.display_dashboard(fillroom(controller1.model)), methods=['POST'])

    def hello(self):
        return render_template('index.html')

    def login(self):
        if request.form['submit'] == 'Login':
            if self.signinObj.go(self.model,request.form['email'],request.form['password']) == "Success":
                return render_template('dashboard.html')
            else:
                return render_template('index.html',message="Login Failed")

    def signup(self):
        return render_template('signup.html')

    def display_questionnaire(self):
        if request.method == 'POST':
            if self.signupObj.go(self.model, request.form['email'], request.form['password']) == "Success":
                self.model.add_User(request.form)
                email = request.form['email']
                if request.form['signupButton'] == 'Find Room':
                    return render_template('findRoomQuestionnaire.html',email=email)
                if request.form['signupButton'] == 'Fill Room':
                    return render_template('fillRoomQuestionnaire.html',email=email)
            else:
                return render_template('signup.html', message="Account already exists")

    def display_dashboard(self,action): #Strategy Pattern
        return action


def findroom(model):
    model.add_findroom(request.form)
    return render_template('dashboard.html')

def fillroom(model):
    model.add_fillroom(request.form)
    return render_template('dashboard.html')
  
if __name__ == "__main__":
    controller1 = Controller()
    controller2 = Controller.getInstance() #Singleton Pattern
    controller2.add_url_rules()
    app.run(debug = True,port=controller1.port)