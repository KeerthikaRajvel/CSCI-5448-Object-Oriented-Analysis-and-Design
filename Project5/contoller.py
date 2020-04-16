import pymongo
from pymongo import MongoClient
from flask import Flask, render_template,request

app = Flask(__name__)
try:
    cluster = MongoClient("mongodb+srv://bunkbud:ooad@cluster0-xythq.mongodb.net/test?retryWrites=true&w=majority")
    print("Connected!")
except:
    print("Counldnt connect!")
db = cluster["bunkbud"]
collection = db["users"]
collection1 = db["findroom"]
collection2 = db["fillroom"]

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
    def aftersignup(self): #Model pattern
        if request.method == 'POST':
            name = request.form['name']
            email = request.form['email']
            password = request.form['password']
            # description = request.form['description']
            gender = request.form['sex']
            age = request.form['age']
            diet = request.form['diet']
            smoker = request.form['smoker']
            drinker = request.form['drinker']
            doc = {"name":name,"email":email,"password":password,"gender":gender,"Age":age,"diet":diet,"smoker":smoker,"drinker":drinker}
            print("Document to be inserted: ",doc)
            x = collection.insert_one(doc)
            if request.form['signupButton'] == 'Find':
                return render_template('findRoomQuestionnaire.html')
                # return render_template('pass.html',name=name,email=email,password=password,description=description,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker)
            if request.form['signupButton'] == 'Fill':
                return render_template('fillRoomQuestionnaire.html')
    def afterfindroom(self):
        date = request.form['date']
        rent = request.form['rent']
        occupancy = request.form['tenants']
        gender = request.form['sex']
        age = request.form['age']
        diet = request.form['diet']
        smoker = request.form['smoker']
        drinker = request.form['drinker']
        music = request.form['music']
        friends = request.form['friends']
        doc = {"date":date,"rent":rent,"occupancy":occupancy,"gender":gender,"age":age,"diet":diet,"smoker":smoker,"drinker":drinker,"music":music,"friends":friends}
        print("Document to be inserted: ",doc)
        x = collection1.insert_one(doc)
        return render_template('dashboard.html')
        # return render_template('pass.html',date=date,rent=rent,occupancy=occupancy,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker,music=music,friends=friends)


    def afterfillroom(self):
        date = request.form['date']
        rent = request.form['rent']
        room = request.form['room']
        occupancy = request.form['tenants']
        gender = request.form['sex']
        age = request.form['age']
        diet = request.form['diet']
        smoker = request.form['smoker']
        drinker = request.form['drinker']
        music = request.form['music']
        friends = request.form['friends']
        doc = {"date":date,"rent":rent,"room":room,"occupancy":occupancy,"gender":gender,"age":age,"diet":diet,"smoker":smoker,"drinker":drinker,"music":music,"friends":friends}
        print("Document to be inserted: ",doc)
        x = collection2.insert_one(doc)
        return render_template('dashboard.html')
        # return render_template('pass.html',date=date,rent=rent,room=room,occupancy=occupancy,gender=gender,age=age,diet=diet,smoker=smoker,drinker=drinker,music=music,friends=friends)


#     def display_questionnaire(self,questionnaire): #Strategy Pattern
#         return questionnaire
#
# def findRoomQuestionnaire():
#     return render_template('findRoomQuestionnaire.html')
# def fillRoomQuestionnaire():
#     return render_template('fillRoomQuestionnaire.html')

controller1 = Controller()
controller2 = Controller.getInstance() #Singleton Pattern
app.add_url_rule('/', 'index', lambda: controller2.hello())
app.add_url_rule('/dashboard','dashboard', lambda: controller2.dashboard(),methods=['POST'])
app.add_url_rule('/signup','signup', lambda: controller2.signup())
app.add_url_rule('/aftersignup','aftersignup', lambda: controller2.aftersignup(),methods=['POST'])
app.add_url_rule('/afterfindroom','afterfindroom', lambda: controller2.afterfindroom(),methods=['POST'])
app.add_url_rule('/afterfillroom','afterfillroom', lambda: controller2.afterfillroom(),methods=['POST'])
# app.add_url_rule('/findRoom','findRoom', lambda: controller2.display_questionnaire(findRoomQuestionnaire()))
# app.add_url_rule('/fillRoom','fillRoom', lambda: controller2.display_questionnaire(fillRoomQuestionnaire()))
app.run(debug = True,port=3400)