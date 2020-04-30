from template import Template
from model import Model

###--- Template Pattern - SingIn Functionality ---###
class Signup(Template):
    password = None

    ###--- Check whether the user already exists or not ---###
    def getCredentials(self,model,email):
        self.password = model.getCredentials(email)

    def authenticate(self, password):
        if self.password == None:
            return "Success"
        else:
            return "Failure"