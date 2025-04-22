package com.github.eventure.view.pages;

import javax.swing.JButton;
// import javax.swing.JPanel;
import javax.swing.JLabel;

// import net.miginfocom.swing.MigLayout;
import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;
// import com.github.eventure.view.windowing.basic.Notification;

public class HomePage extends Page {
    public HomePage() {
        super(PageLayouts.REGULAR_LAYOUT);
        setDefault(true); // Essa página será a padrão ao iniciar o app

        // Título da página
        // add(new JLabel("GALERIA"), "span");
        // add(new JLabel("Example 1"), "wrap");

        // Painel de botões (comentado para manter só o botão de cadastro)
        // var p = new JPanel(new MigLayout("fillx", PageLayouts.REGULAR_LAYOUT));
        var cadastroUsuarioBtn = new JButton("Cadastrar");
        cadastroUsuarioBtn.addActionListener(event -> {
            switchPage(getPageId(RegisterPage.class));
        });
        add(cadastroUsuarioBtn, "center");

        var loginBtn = new JButton("Entrar");
        loginBtn.addActionListener(e -> {switchPage(getPageId(LoginPage.class));});
        add(loginBtn, "center");

        // Exemplo de botões anteriores (comentados)
        // p.add(new JButton("Button 1"), "split 1");
        // var b2 = new JButton("Button 2");
        // b2.addActionListener(event -> switchPage(getPageId(SecondaryPage.class)));
        // p.add(b2, "wrap");
        // p.add(b, "growx, span");

        // add(p, "");
        // add(new JLabel("Example 3"), "span");
    }
}
