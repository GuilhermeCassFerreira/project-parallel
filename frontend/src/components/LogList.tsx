import React from 'react';

const LogList: React.FC<{ logs: any[] }> = ({ logs }) => {
  return (
    <ul>
      {logs.map((log, index) => (
        <li key={index}>
          {log.timestamp}: {log.message}
        </li>
      ))}
    </ul>
  );
};

export default LogList;