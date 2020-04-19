from template import Template
from model import Model

class Signup(Template):
    password = None

    def getCredentials(self,model,email):
        self.password = model.getCredentials(email)

    def authenticate(self, password):
        if self.password == None:
            return "Success"
        else:
            return "Failure"