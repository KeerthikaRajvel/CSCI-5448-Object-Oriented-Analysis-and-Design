from flask import Flask, render_template,request
from model import Model
from signin import Signin
from signup import Signup
from matchingAlgo import MatchingAlgo

##--Starting the Web Server--##
app = Flask(__name__)

##-- MVC Pattern --##
class Controller:

    instance=None
    model=None
    port=None
    signinObj=None
    signupObj=None
    matchingObj = None

    ##-- Singleton Pattern --##
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

            ##--- HAS-A Relationship (Composition) ---###
            Controller.signupObj = Signup()
            Controller.signinObj = Signin()
            Controller.matchingObj = MatchingAlgo()

    def add_url_rules(self):
        app.add_url_rule('/', 'index', lambda: controller2.hello())
        app.add_url_rule('/signup', 'signup', lambda: controller2.signup())
        app.add_url_rule('/login', 'login', lambda: controller2.login(),methods=['POST'])
        app.add_url_rule('/questionnaire', 'questionnaire', lambda: controller2.display_questionnaire(),methods=['POST'])
        app.add_url_rule('/matching', 'matching', lambda: controller2.matching_signup(),methods=['POST'])
       
    ##-- Login Page --##
    def hello(self):
        return render_template('index.html')

    ##--- Verifying existing user crdentials and displaying their Matches Dashboard ---##
    def login(self):
        if request.form['submit'] == 'Login':
            if self.signinObj.go(self.model,request.form['email'],request.form['password']) == "Success":
                type = self.model.findroom_collection.find({"email": request.form['email']})
                print(type.count())
                if(type.count() > 0):
                    options = self.matchingObj.findMatch(type, 'fill' )
                else :
                    type = self.model.fillroom_collection.find({"email": request.form['email']})
                    options = self.matchingObj.findMatch(type, 'find' )
                return render_template('dashboard.html', options=options)

    ##-- SignUp Page--##
    def signup(self):
        return render_template('signup.html')

    ###--- Strategy Pattern ---###
    ##--- Verifying and Storing new user info using Model Class --##
    ##--- Directing New Users to fill their preferences eother Fill a Room or Find a Room
    def display_questionnaire(self):
        if request.method == 'POST':
            if self.signupObj.go(self.model, request.form['email'], request.form['password']) == "Success":
                self.model.add_User(request.form)
                email = request.form['email']
                if request.form['signupButton'] == 'Find Room':
                    return render_template('findRoomQuestionnaire.html',email=email)
                elif request.form['signupButton'] == 'Fill Room':
                    return render_template('fillRoomQuestionnaire.html',email=email)
            else:
                return render_template('signup.html', message="Account already exists")

    ##-- Calling the Matcing Algo after SignUp and displaying the Match Dashboard --##
    def matching_signup(self):
        if request.method == 'POST':
            if request.form['Submit'] == 'Fill/Submit':
                self.model.add_fillroom(request.form)
                type = self.model.fillroom_collection.find({"email": request.form['email']})
                options = self.matchingObj.findMatch(type, 'find' )
            elif request.form['Submit'] == 'Find/Submit':
                self.model.add_findroom(request.form)
                type = self.model.findroom_collection.find({"email": request.form['email']})
                options = self.matchingObj.findMatch(type, 'fill' )
            if (options):
                return render_template('dashboard.html', options=options)
            else:
                return render_template('index.html',message="Error SignUp!!!!")

  
if __name__ == "__main__":
    controller1 = Controller()
    controller2 = Controller.getInstance() #Singleton Pattern
    controller2.add_url_rules()
    app.run(debug = True,port=controller1.port)
