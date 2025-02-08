import React, { useState, useEffect } from 'react';
import ProcessForm from './components/ProcessForm.tsx'; // ou './components/ProcessForm.js'
import ProcessList from './components/ProcessList.tsx'; // ou './components/ProcessList.js'
import LogList from './components/LogList.tsx'; // ou './components/LogList.js'
import { connect, disconnect } from './services/websocket.ts';

const App: React.FC = () => {
  const [logs, setLogs] = useState<any[]>([]);

  useEffect(() => {
    connect((message) => {
      setLogs((prevLogs) => [...prevLogs, message]);
    });
    return () => {
      disconnect();
    };
  }, []);

  const handleProcessCreated = () => {
    // Recarregar a lista de processos
  };

  return (
    <div>
      <h1>Simulador de Processos em Tempo Real</h1>
      <ProcessForm onProcessCreated={handleProcessCreated} />
      <h2>Processos Ativos</h2>
      <ProcessList onLogsReceived={setLogs} />
      <h2>Logs em Tempo Real</h2>
      <LogList logs={logs} />
    </div>
  );
};

export default App;