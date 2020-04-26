from pymongo import MongoClient
class Model:
    def __init__(self):
        try:
            cluster = MongoClient("mongodb+srv://bunkbud:ooad@cluster0-xythq.mongodb.net/test?retryWrites=true&w=majority")
            print("Connected!")
        except:
            print("Counldnt connect!")

        self.db = cluster["bunkbud"]
        self.users_collection = self.db["users"]
        self.findroom_collection = self.db["findroom"]
        self.fillroom_collection = self.db["fillroom"]
        self.email = ""

    def add_User(self,record):
        name = record['name']
        email = record['email']
        self.email = email
        password = record['password']
        # description = request.form['description']
        gender = record['sex']
        age = record['age']
        diet = record['diet']
        smoker = record['smoker']
        drinker = record['drinker']
        doc = {"name": name, "email": email, "password": password, "gender": gender, "Age": age, "diet": diet,
               "smoker": smoker, "drinker":drinker}
        self.users_collection.insert_one(doc)

    #def get_User(self, email):
    #    return self.users_collection.find({"email": email})


    def add_findroom(self,record):
        email = record['email']
        date = record['date']
        rent = record['rent']
        occupancy = record['tenants']
        gender = record['sex']
        age = record['age']
        diet = record['diet']
        smoker = record['smoker']
        drinker = record['drinker']
        music = record['music']
        friends = record['friends']
        doc = {"email":email,"date": date, "rent": rent, "occupancy": occupancy, "gender": gender, "age": age, "diet": diet,
               "smoker": smoker, "drinker": drinker, "music": music, "friends": friends}
        self.findroom_collection.insert_one(doc)
    
    def get_findroom(self, user_details):
        findroom_details = self.findroom_collection.find({"email": self.email})
        if user_details!=None:
            for r in user_details:
                diet= r['diet']
            fillroom_details = self.fillroom_collection.find({"diet":diet})
        return user_details, findroom_details, fillroom_details

    def add_fillroom(self,record):
        email = record['email']
        date = record['date']
        rent = record['rent']
        room = record['room']
        occupancy = record['tenants']
        gender = record['sex']
        age = record['age']
        diet = record['diet']
        smoker = record['smoker']
        drinker = record['drinker']
        music = record['music']
        friends = record['friends']
        doc = {"email":email,"date": date, "rent": rent, "room": room, "occupancy": occupancy, "gender": gender, "age": age,
               "diet": diet, "smoker": smoker, "drinker": drinker, "music": music, "friends": friends}
        print("Document to be inserted: ", doc)
        self.fillroom_collection.insert_one(doc)

    def getCredentials(self,email): #Fetching the password for a given email id
        record = self.users_collection.find({"email":email})
        if record!=None:
            for r in record:
                return r['password']
        else:
            return None