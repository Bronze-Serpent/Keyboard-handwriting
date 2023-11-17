package com.ivancha.gui.panel;

import com.ivancha.dto.PasswordCreateDto;
import com.ivancha.dto.PasswordReadDto;
import com.ivancha.dto.UserCreateDto;
import com.ivancha.dto.UserReadDto;
import com.ivancha.gui.listener.KeyStatisticsCollector;
import com.ivancha.service.PasswordService;
import com.ivancha.service.UserService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrationPanel extends JPanel {

    private final UserService userService;
    private final PasswordService passwordService;


    public RegistrationPanel(UserService userService, PasswordService passwordService) {

        super(new MigLayout());

        this.userService = userService;
        this.passwordService = passwordService;

        JLabel registerLbl = new JLabel("Зарегистрируемся");
        this.add(registerLbl, "span, center, gapbottom 15");

        JLabel usernameLbl = new JLabel("Введите имя пользователя: ");
        JTextField usernameTextField = new JTextField(20);
        this.add(usernameLbl);
        this.add(usernameTextField, "wrap");

        JLabel passwordLbl = new JLabel("Введите пароль: ");
        this.add(passwordLbl);
        JTextField passwordTextField = new JTextField(10); // принимает до 10 символов
        KeyStatisticsCollector keyStatistics = new KeyStatisticsCollector();
        passwordTextField.addKeyListener(keyStatistics);
        this.add(passwordTextField, "wrap");

        var sendButtonListener = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                var userId = userService.create(new UserCreateDto(usernameTextField.getText()));

                List<Long> timeBetweenPresses = keyStatistics.getTimeBetweenPresses();
                timeBetweenPresses.remove(0);
                passwordService.create(new PasswordCreateDto(
                        passwordTextField.getText(),
                        userId,
                        listToMapWithOrder(timeBetweenPresses),
                        listToMapWithOrder(keyStatistics.getPressTimeList()))
                );

                usernameTextField.setText("");
                passwordTextField.setText("");
            }

            public Map<Integer, Integer> listToMapWithOrder(List<Long> list) {
                Map<Integer, Integer> map = new HashMap<>();

                for (int i = 0; i < list.size(); i++)
                    map.put(i, list.get(i).intValue());

                return map;
            }
        };

        var showAllUserListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuilder sb = new StringBuilder();
                var userInfoList = userService.findAll();

                for (int i = 0; i < userInfoList.size(); i++) {

                    UserReadDto userDto = userInfoList.get(i);
                    PasswordReadDto passwordDto = userDto.passwordReadDto();
                    sb.append(i).append(": ");
                    sb.append("Имя пользователя: ").append(userDto.nickname());
                    sb.append("\tПароль: ").append(passwordDto.value());
                    sb.append("\tВремя нажатия клавиш: ").append(passwordDto.keyPressTime());
                    sb.append("\tВремя между нажатиями клавиш: ").append(passwordDto.timeBetweenPresses());
                }
                JOptionPane.showMessageDialog(RegistrationPanel.this, sb.toString());
            }
        };

        JButton sendBtn = new JButton("Отправить");
        sendBtn.addActionListener(sendButtonListener);
        this.add(sendBtn, "span, gapbottom 15");

        JButton showAllUsersBtn = new JButton("Показать всех зарегистрированных пользователей");
        showAllUsersBtn.addActionListener(showAllUserListener);
        this.add(showAllUsersBtn, "span");
    }
}
