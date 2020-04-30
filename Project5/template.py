###--- Template pattern ---###
class Template:

    def getCredentials(self,model,email):
        pass

    def authenticate(self,password):
        pass
    
    ###--- Carries out the steps required to Login or SignUp ---###
    def go(self,model,email,password):

        self.getCredentials(model,email)
        return self.authenticate(password)