package client.ui;

import client.domain.ClientController;

public interface ClientView {

    /**
     * ����� ��� ����������� ��������� � GUI
     * @param message ����� ���������
     */
    void showMessage(String message);

    /**
     * ����� ���������� �� ������� �� ������� �������
     */
    void disconnectedFromServer();

    /**
     * ������
     * @param clientController ������, ����������� ������ ���������
     */
    void setClientController(ClientController clientController);
}