from template import Template
from model import Model

class Signin(Template):
    password=None

    def getCredentials(self,model,email):
        self.password = model.getCredentials(email)

    def authenticate(self,password):
        if self.password!=None and self.password==password:
            return "Success"
        else:
            return "Failure"
