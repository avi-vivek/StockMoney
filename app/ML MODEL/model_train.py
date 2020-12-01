import math
import pandas_datareader.data as web
import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import Dense, LSTM
from keras.layers import Dropout
from keras import backend as K
from datetime import date
import pandas as pd
import numpy as np
from sklearn.neural_network import MLPRegressor
from datetime import time
import pandas as pd
import time

# Get the stock quote
df = web.DataReader('TATAMOTORS.NS', data_source='yahoo',start = '2000-01-25', end = date.today())

df['Date'] = df.index

# Create new dataframe with only close coloumn
data = df.filter(['Adj Close'])
# Convert dataframe to numpy array
dataset = data.values
# Get the number of rows to train the model on
training_data_len = math.ceil( len(dataset))

# Scale the data
scaler = MinMaxScaler(feature_range=(0,1))
scaled_data = scaler.fit_transform(dataset)
# Create training datasets
# Create the scaled training datasets
train_data = scaled_data[0:training_data_len,:]
# Slit x_train and y_train datasets
x_train = []
y_train = []
for i in range(100,len(train_data)):
    x_train.append(train_data[i-100:i,0])
    y_train.append(train_data[i,0])

# convert x_train y_train to numpy arrays
x_train, y_train = np.array(x_train), np.array(y_train)

# Reshape the data
x_train = np.reshape(x_train,(x_train.shape[0], x_train.shape[1],1))
# Build LSTM Model
model = Sequential()
model.add(LSTM(50,return_sequences=True, input_shape = (x_train.shape[1],1)))
model.add(Dropout(0.3))
model.add(LSTM(50, return_sequences=False))
model.add(Dropout(0.3))
model.add(Dense(1))

model.compile(optimizer='adam',loss='mse')

history = model.fit(x_train,y_train,batch_size=5, epochs = 3,)

# pickle.dump(model, open('model.pkl','wb'))
# model = pickle.load(open('model.pkl','rb'))

model.save("model.h5")