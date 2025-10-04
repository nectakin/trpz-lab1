package org.audioeditor;

import org.audioeditor.repository.AudioRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioEditorUI {
    private JFrame frame;
    private AudioRepository repository;

    public AudioEditorUI() {
        repository = new AudioRepository();
        frame = new JFrame("Audio Editor");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Кнопка для вибору файлу
        JButton addButton = new JButton("Add Audio");
        addButton.setBounds(50, 50, 150, 30);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Audio File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Додаємо файл у репозиторій
                    repository.addAudio(selectedFile.getName(),
                            getFileExtension(selectedFile),
                            selectedFile.getAbsolutePath());
                    JOptionPane.showMessageDialog(frame, "Audio added: " + selectedFile.getName());
                } else {
                    JOptionPane.showMessageDialog(frame, "No file selected");
                }
            }
        });

        // Кнопка для перегляду аудіо
        JButton viewButton = new JButton("View Audios");
        viewButton.setBounds(50, 100, 150, 30);
        frame.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String audios = String.join(", ", repository.getAllAudio());
                if (audios.isEmpty()) {
                    audios = "No audio files added yet.";
                }
                JOptionPane.showMessageDialog(frame, "Audios: " + audios);
            }
        });

        frame.setVisible(true);
    }

    // Допоміжний метод для отримання розширення файлу
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex > 0 && lastIndex < name.length() - 1) {
            return name.substring(lastIndex + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        new AudioEditorUI();
    }
}
