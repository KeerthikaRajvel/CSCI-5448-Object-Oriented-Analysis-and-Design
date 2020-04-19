#Template pattern
class Template:

    def getCredentials(self,model,email):
        pass

    def authenticate(self,password):
        pass

    def go(self,model,email,password):

        self.getCredentials(model,email)
        return self.authenticate(password)