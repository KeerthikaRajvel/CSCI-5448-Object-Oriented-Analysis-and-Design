from template import Template
from model import Model

###--- Template Pattern - Login Functionality ---###
class Signin(Template):
    password=None

    ###--- Retrieve password of a user based on their emailid ---###
    def getCredentials(self,model,email):
        self.password = model.getCredentials(email)

    ###--- Authenticate the user based on the password
    def authenticate(self,password):
        if self.password!=None and self.password==password:
            return "Success"
        else:
            return "Failure"
