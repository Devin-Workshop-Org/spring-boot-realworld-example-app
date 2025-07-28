import React, { useState } from 'react';
import TextDataForm from './components/TextDataForm';
import TextDataList from './components/TextDataList';
import './App.css';

const App: React.FC = () => {
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  const handleSuccess = () => {
    setRefreshTrigger(prev => prev + 1);
  };

  return (
    <div className="container">
      <h1>Text Data Manager</h1>
      
      <TextDataForm onSuccess={handleSuccess} />
      
      <TextDataList refreshTrigger={refreshTrigger} />
    </div>
  );
};

export default App;
