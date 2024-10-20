package client.domain;

import client.ui.ClientView;
import server.domain.ServerController;

public class ClientController {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController serverController;

    public ClientController(ClientView clientView, ServerController serverController) {
        this.clientView = clientView;
        this.serverController = serverController;
        clientView.setClientController(this);
    }

    /**
     * ����� ������� ����������� � �������. ���������� �� GUI
     * @param name ��� ������������, ������� ����� ����������� ��������� ���������
     * @return ����� �� �������. ���������� true, ���� ������ �����������
     */
    public boolean connectToServer(String name) {
        this.name = name;
        if (serverController.connectUser(this)){
            showOnWindow("�� ������� ������������!\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("����������� �� �������");
            return false;
        }
    }

    /**
     * �����, � ������� �������� ������ �������� ������� ���������
     * @param text ����� ���������� �� �������
     */
    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    /**
     * ����� ���������� �� ������� ������������������ ��������
     */
    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("�� ���� ��������� �� �������!");
        }
    }

    /**
     * ����� ���������� �� ������� ������������������ �������� (�������� ������� GUI)
     */
    public void disconnectServer() {
        serverController.disconnectUser(this);
    }

    /**
     * ����� ��� �������� ��������� �� ������
     * @param text ����� ������������� ���������
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(name + ": " + text);
            }
        } else {
            showOnWindow("��� ����������� � �������");
        }
    }

    /**
     * ������
     * @return ���������� ��� �������
     */
    public String getName() {
        return name;
    }

    /**
     * ����� ������ ��������� �� GUI
     * @param text �����, ������� ��������� ������� �� �����
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }
}
