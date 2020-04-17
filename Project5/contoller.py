from flask import Flask, render_template,request
from model import Model

app = Flask(__name__)

class Controller:

    instance=None
    model=None
    port=None

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

    def add_url_rules(self):
        app.add_url_rule('/', 'index', lambda: controller2.hello())
        app.add_url_rule('/signup', 'signup', lambda: controller2.signup())
        app.add_url_rule('/questionnaire', 'questionnaire', lambda: controller2.display_questionnaire(),methods=['POST'])
        app.add_url_rule('/findroom_dashboard', 'findroom_dashboard',lambda: controller2.display_dashboard(findroom(controller1.model)), methods=['POST'])
        app.add_url_rule('/fillroom_dashboard', 'fillroom_dashboard',lambda: controller2.display_dashboard(fillroom(controller1.model)), methods=['POST'])

    def hello(self):
        return render_template('index.html')

    def dashboard(self):
        return render_template('dashboard.html')

    def signup(self):
        return render_template('signup.html')

    def display_questionnaire(self):
        if request.method == 'POST':
            self.model.add_User(request.form)
            if request.form['signupButton'] == 'Find Room':
                return render_template('findRoomQuestionnaire.html')
                # return render_template('pass.html',name=name,email=email,password=password,description=description,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker)
            if request.form['signupButton'] == 'Fill Room':
                return render_template('fillRoomQuestionnaire.html')


    def display_dashboard(self,action): #Strategy Pattern
        return action


def findroom(model):
    model.add_findroom(request.form)
    return render_template('dashboard.html')
    # return render_template('pass.html',date=date,rent=rent,occupancy=occupancy,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker,music=music,friends=friends)

def fillroom(model):
    model.add_fillroom(request.form)
    return render_template('dashboard.html')
    # return render_template('pass.html',date=date,rent=rent,room=room,occupancy=occupancy,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker,music=music,friends=friends)

if __name__ == "__main__":
    controller1 = Controller()
    controller2 = Controller.getInstance() #Singleton Pattern
    controller2.add_url_rules()
    app.run(debug = True,port=controller1.port)