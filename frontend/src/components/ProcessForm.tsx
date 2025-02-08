import React, { useState } from 'react';
import { createProcess } from '../services/api.ts';

const ProcessForm: React.FC<{ onProcessCreated: () => void }> = ({ onProcessCreated }) => {
  const [name, setName] = useState('');
  const [command, setCommand] = useState('');
  const [parallel, setParallel] = useState(false);
  const [tasks, setTasks] = useState(1);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    const process = { name, command, parallel, tasks };
    await createProcess(process);
    onProcessCreated();
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Nome do Processo:
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
      </label>
      <label>
        Comando:
        <input type="text" value={command} onChange={(e) => setCommand(e.target.value)} required />
      </label>
      <label>
        Paralelo:
        <input type="checkbox" checked={parallel} onChange={(e) => setParallel(e.target.checked)} />
      </label>
      <label>
        Número de Tarefas:
        <input type="number" value={tasks} onChange={(e) => setTasks(parseInt(e.target.value))} required />
      </label>
      <button type="submit">Criar Processo</button>
    </form>
  );
};

export default ProcessForm;