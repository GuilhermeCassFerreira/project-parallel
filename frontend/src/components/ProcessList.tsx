import React, { useEffect, useState } from 'react';
import { listProcesses, getProcessStatus, getProcessLogs } from '../services/api.ts';

const ProcessList: React.FC<{ onLogsReceived: (logs: any[]) => void }> = ({ onLogsReceived }) => {
  const [processes, setProcesses] = useState<any[]>([]);

  useEffect(() => {
    const fetchProcesses = async () => {
      try {
        const response = await listProcesses();
        setProcesses(response.data);
      } catch (error) {
        console.error('Erro ao buscar processos:', error);
      }
    };
    fetchProcesses();
  }, []);

  const handleProcessClick = async (id: number) => {
    try {
      const statusResponse = await getProcessStatus(id);
      console.log('Status do processo:', statusResponse.data);

      const logsResponse = await getProcessLogs(id);
      onLogsReceived(logsResponse.data);
    } catch (error) {
      console.error('Erro ao buscar status ou logs do processo:', error);
    }
  };

  return (
    <ul>
      {processes.map((process) => (
        <li key={process.id} onClick={() => handleProcessClick(process.id)}>
          {process.name} - {process.status}
        </li>
      ))}
    </ul>
  );
};

export default ProcessList;