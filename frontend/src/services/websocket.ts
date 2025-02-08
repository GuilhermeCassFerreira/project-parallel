import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

let stompClient: any = null;

export const connect = (onMessageReceived: (message: any) => void) => {
  const socket = new SockJS('http://localhost:8080/ws');
  stompClient = Stomp.over(socket);

  stompClient.connect({}, (frame: any) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/logs', (message: any) => {
      onMessageReceived(JSON.parse(message.body));
    });
  });
};

export const disconnect = () => {
  if (stompClient !== null && stompClient.connected) {
    stompClient.disconnect(() => {
      console.log('Disconnected');
    });
  }
};