import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const createProcess = (process: any) => api.post('/process/create', process);
export const listProcesses = () => api.get('/process/list');
export const getProcessStatus = (id: number) => api.get(`/process/status/${id}`);
export const getProcessLogs = (id: number) => api.get(`/process/logs/${id}`);