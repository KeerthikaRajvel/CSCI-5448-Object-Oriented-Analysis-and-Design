from model import Model
from datetime import datetime, timedelta
import operator

class MatchingAlgo():
    userInfo = None
    matchUsers = None
    type = None
    model = None

    def __init__(self):
        self.model = Model()
        self.matchUsers = []

    def findMatch(self, temp, opt):

        #Fetching User Details
        for t in temp:
            self.userInfo = t

        #Fetching Options Details
        if(opt == 'fill'):
            self.type = self.model.fillroom_collection.find()
        else:
            self.type = self.model.findroom_collection.find()

        opt = {}
        for user in self.type:
            score = 0
            #Checking Date
            if ( datetime.strptime(self.userInfo['date'], '%Y-%m-%d') - timedelta(days=5) <= datetime.strptime(user['date'], '%Y-%m-%d') and datetime.strptime(self.userInfo['date'], '%Y-%m-%d') + timedelta(days=5) >= datetime.strptime(user['date'], '%Y-%m-%d') ):
                score += 20
            elif (datetime.strptime(self.userInfo['date'], '%Y-%m-%d') - timedelta(days=14) <= datetime.strptime(user['date'], '%Y-%m-%d') and datetime.strptime(self.userInfo['date'], '%Y-%m-%d') + timedelta(days=14) >= datetime.strptime(user['date'], '%Y-%m-%d') ):
                score += 10
            elif (datetime.strptime(self.userInfo['date'], '%Y-%m-%d') - timedelta(days=21) <= datetime.strptime(user['date'], '%Y-%m-%d') and datetime.strptime(self.userInfo['date'], '%Y-%m-%d') + timedelta(days=21) >= datetime.strptime(user['date'], '%Y-%m-%d') ):
                score += 5
            #Checking Rent
            if (self.userInfo['rent'] == user['rent']):
                score += 15
            elif ( (self.userInfo['rent']=='less' and user['rent']=='mid') or (self.userInfo['rent']=='mid' and user['rent']=='less') or (self.userInfo['rent']=='mid' and user['rent']=='high') or (self.userInfo['rent']=='high' and user['rent']=='mid') ):
                score += 8
            else:
                score += 5
            #Checking Occupancy (Different) - for user finding a room
            if(opt == 'fill'):
                if ( (self.userInfo['occupancy']=='alone' and user['occupancy']=='0') or (self.userInfo['occupancy']=='people' and (user['occupancy']=='one' or user['occupancy']=='>2r')) ):
                    score += 15
                else:
                    score += 5
            #Checking Occupancy (Different) - for user filling a room
            else:
                if ( (self.userInfo['occupancy']=='0' and user['occupancy']=='alone') or ( (self.userInfo['occupancy']=='one' or self.userInfo['occupancy']=='>2r') and user['occupancy']=='people' ) ):
                    score += 15
                else:
                    score += 5
            #Checking Gender
            if ( self.userInfo['gender']=='Either' or (self.userInfo['gender']==user['gender']) or user['gender']=='Either' ):
                score += 12
            elif (self.userInfo['gender']!=user['gender']):
                score += 5
            #Checking Age
            if ( self.userInfo['age']=='nothing' or (self.userInfo['age']==user['age']) or user['age']=='nothing' ):
                score += 12
            elif ( (self.userInfo['age']=='Teen' and user['age']=='Adult') or (self.userInfo['age']=='Adult' and user['age']=='Teen') or (self.userInfo['age']=='Adult' and user['age']=='Old') or (self.userInfo['age']=='Old' and user['age']=='Adult') ):
                score += 6
            else:
                score += 4
            #Checking Diet
            if ( self.userInfo['diet']=='None' or (self.userInfo['diet']==user['diet']) or user['diet']=='None' ):
                score += 10
            elif ( (self.userInfo['diet']=='Vegan' and user['diet']=='Vegetarian') or (self.userInfo['diet']=='Vegetarian' and user['diet']=='Vegan') ):
                score += 8
            else:
                score += 5
            #Checking Smoker
            if( self.userInfo['smoker']!=user['smoker'] ):
                score += 5
            else:
                score += 2
            #Checking Drinker
            if( self.userInfo['drinker']!=user['drinker'] ):
                score += 5
            else:
                score += 2
            #Checking Music
            if( user['music'] == '0' ):
                score += 3
            else:
                score += 1
            #Checking Friends
            if( user['friends'] == '0' ):
                score += 3
            else:
                score += 1
            #Appending User and Score
            opt[user['email']] = score
        
        #Returning the Top 4 results
        count = 1
        options = {}
        while(count <=4 and opt):
            a = max(opt.items(), key=operator.itemgetter(1))[0]
            options[a] = opt[a]
            del opt[a]
            count +=1
        return options
        

