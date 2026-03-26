package com.donations;

import java.time.LocalDate;
import java.util.List;

import com.donations.dao.BeneficiarioDAO;
import com.donations.dao.CampanhaDAO;
import com.donations.dao.DoacaoDAO;
import com.donations.dao.DoadorDAO;
import com.donations.dao.EmpresaDAO;
import com.donations.dao.EnderecoDAO;
import com.donations.dao.EntregaDAO;
import com.donations.dao.ItemEstoqueDAO;
import com.donations.dao.OngDAO;
import com.donations.dao.ParticipacaoDAO;
import com.donations.dao.PatrocinioDAO;
import com.donations.dao.TelefoneDAO;
import com.donations.dao.VoluntarioDAO;
import com.donations.model.Beneficiario;
import com.donations.model.Campanha;
import com.donations.model.Doacao;
import com.donations.model.Doador;
import com.donations.model.Empresa;
import com.donations.model.Endereco;
import com.donations.model.Entrega;
import com.donations.model.ItemEstoque;
import com.donations.model.ONG;
import com.donations.model.Participacao;
import com.donations.model.Patrocinio;
import com.donations.model.TelefoneDoador;
import com.donations.model.TelefoneONG;
import com.donations.model.Voluntario;
import com.donations.util.FormatadorUtil;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private DoacaoDAO doacaoDAO = new DoacaoDAO();
    private DoadorDAO doadorDAO = new DoadorDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private CampanhaDAO campanhaDAO = new CampanhaDAO();
    private OngDAO ongDAO = new OngDAO();
    private VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
    private ItemEstoqueDAO estoqueDAO = new ItemEstoqueDAO();
    private BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
    private EntregaDAO entregaDAO = new EntregaDAO();
    private EmpresaDAO empresaDAO = new EmpresaDAO();
    private PatrocinioDAO patrocinioDAO = new PatrocinioDAO();
    private ParticipacaoDAO participacaoDAO = new ParticipacaoDAO();
    private TelefoneDAO telefoneDAO = new TelefoneDAO();

    private TableView<Doacao> tabelaDoacoes = new TableView<>();
    private TableView<ItemEstoque> tabelaEstoque = new TableView<>();
    private Label lblTotal = new Label("Total Arrecadado: 0.00 Vol.");
    
    private ComboBox<Endereco> cbEndDoador = new ComboBox<>(), cbEndOng = new ComboBox<>(), 
                               cbEndCampanha = new ComboBox<>(), cbEndVoluntario = new ComboBox<>(), 
                               cbEndBeneficiario = new ComboBox<>(), cbEndEmpresa = new ComboBox<>();

    private ComboBox<Doador> cbDoadorDoacao = new ComboBox<>(), cbDoadorTelefone = new ComboBox<>();
    private ComboBox<Campanha> cbCampanhaDoacao = new ComboBox<>(), cbCampanhaPatrocinio = new ComboBox<>(), cbCampanhaParticipacao = new ComboBox<>();
    private ComboBox<ONG> cbOngCampanha = new ComboBox<>(), cbOngEstoque = new ComboBox<>(), cbOngTelefone = new ComboBox<>();
    private ComboBox<Beneficiario> cbBeneficiarioEntrega = new ComboBox<>();
    private ComboBox<ItemEstoque> cbItemEntrega = new ComboBox<>();
    private ComboBox<Empresa> cbEmpresaPatrocinio = new ComboBox<>();
    private ComboBox<Voluntario> cbVoluntarioParticipacao = new ComboBox<>();

    @Override
    public void start(Stage stage) {
        TabPane root = new TabPane();
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.setStyle("-fx-font-family: 'Segoe UI', system-ui; -fx-font-size: 13px;");

        Tab tabDoacoes = new Tab("🎁 Doações", criarPainelDoacao());
        Tab tabEstoque = new Tab("📦 Estoque", criarPainelEstoque());
        Tab tabEntregas = new Tab("🚚 Entregas", criarPainelEntrega());

        Tab tabOngs = new Tab("🏢 ONGs", criarPainelOng());
        Tab tabEmpresas = new Tab("🤝 Parceiros", criarPainelEmpresa());
        Tab tabPatrocinios = new Tab("💼 Patrocínios", criarPainelPatrocinio());
        Tab tabCampanhas = new Tab("📢 Campanhas", criarPainelCampanha());

        Tab tabDoadores = new Tab("👥 Doadores", criarPainelDoador());
        Tab tabFamilias = new Tab("👨‍👩‍👧 Beneficiários", criarPainelBeneficiario());
        Tab tabVoluntarios = new Tab("🙋 Voluntários", criarPainelVoluntario());
        Tab tabParticipacao = new Tab("⏱️ Participações", criarPainelParticipacao());

        Tab tabTelefones = new Tab("📞 Contatos", criarPainelTelefones());
        Tab tabEnderecos = new Tab("📍 Endereços", criarPainelEndereco());

        root.getTabs().addAll(
            tabDoacoes, tabEstoque, tabEntregas, new Tab("---", new Label()), // Divisor visual
            tabOngs, tabEmpresas, tabPatrocinios, tabCampanhas,
            tabDoadores, tabFamilias, tabVoluntarios, tabParticipacao,
            tabTelefones, tabEnderecos
        );
        
        Scene scene = new Scene(root, 1280, 850);
        
        // CSS para destacar a aba selecionada
        scene.getStylesheets().add("data:text/css," + 
            ".tab-pane .tab-header-area .tab-selected { -fx-background-color: #3498db; }" +
            ".tab-pane .tab-header-area .tab-selected .tab-label { -fx-text-fill: white; -fx-font-weight: bold; }");

        atualizarTudo();

        stage.setScene(scene);
        stage.setTitle("Sistema de Gerenciamento de Doações");
        stage.show();
    }

    private VBox criarPainelDoacao() {
        VBox layout = new VBox(20); 
        layout.setPadding(new Insets(25)); 
        layout.setAlignment(Pos.TOP_CENTER);
        lblTotal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");
        
        GridPane form = new GridPane(); 
        form.setHgap(15); 
        form.setVgap(10); 
        form.setAlignment(Pos.CENTER);
        TextField txtTipo = new TextField(); 
        TextField txtVol = new TextField();
        
        form.add(new Label("Doador:"), 0, 0); 
        form.add(cbDoadorDoacao, 1, 0);
        form.add(new Label("Campanha:"), 0, 1); 
        form.add(cbCampanhaDoacao, 1, 1);
        form.add(new Label("Item:"), 2, 0); 
        form.add(txtTipo, 3, 0);
        form.add(new Label("Volume / Quantidade:"), 2, 1); 
        form.add(txtVol, 3, 1);

        Button btnAdd = new Button("✚ Registrar Doação"); 
        btnAdd.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        
        btnAdd.setOnAction(e -> {
            try {
                validarCombo(cbDoadorDoacao, "Doador"); validarCombo(cbCampanhaDoacao, "Campanha");
                Doacao d = new Doacao(0, txtTipo.getText(), Double.parseDouble(txtVol.getText().replace(",", ".")), 
                                      new java.sql.Date(System.currentTimeMillis()), "Balcão", 
                                      cbDoadorDoacao.getValue().getId(), cbCampanhaDoacao.getValue().getId());
                doacaoDAO.salvar(d); 
                atualizarTudo(); 
                txtTipo.clear(); 
                txtVol.clear();
            } catch (Exception ex) { mostrarAlerta("Erro", ex.getMessage()); }
        });

        configurarTabelaDoacoes();
        layout.getChildren().addAll(lblTotal, new Separator(), form, btnAdd, tabelaDoacoes);
        return layout;
    }

    private VBox criarPainelEstoque() {
        VBox layout = new VBox(20); 
        layout.setPadding(new Insets(25)); 
        layout.setAlignment(Pos.TOP_CENTER);
        GridPane form = new GridPane(); 
        form.setHgap(15); form.setVgap(15); 
        form.setAlignment(Pos.CENTER);
        TextField txtItem = new TextField(); 
        TextField txtQtd = new TextField(); 
        TextField txtUnd = new TextField();
        
        form.add(new Label("ONG de Destino:"), 0, 0); 
        form.add(cbOngEstoque, 1, 0);
        form.add(new Label("Nome do Item:"), 2, 0); 
        form.add(txtItem, 3, 0);
        form.add(new Label("Quantidade:"), 0, 1); 
        form.add(txtQtd, 1, 1);
        form.add(new Label("Unidade (Ex: kg, un):"), 2, 1); 
        form.add(txtUnd, 3, 1);

        Button btn = new Button("📦 Atualizar Estoque");
        btn.setStyle("-fx-background-color: #d35400; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setOnAction(e -> {
            try {
                validarCombo(cbOngEstoque, "ONG");
                ItemEstoque item = new ItemEstoque(0, txtItem.getText(), Double.parseDouble(txtQtd.getText()), txtUnd.getText(), cbOngEstoque.getValue().getId());
                estoqueDAO.salvarOuSomar(item); 
                atualizarTudo(); 
                txtItem.clear(); 
                txtQtd.clear();
            } catch (Exception ex) { 
                mostrarAlerta("Erro", ex.getMessage()); 
            }
        });

        configurarTabelaEstoque();
        layout.getChildren().addAll(new Label("Controle de Armazenamento"), form, btn, tabelaEstoque);
        return layout;
    }

    private VBox criarPainelEntrega() {
        VBox layout = new VBox(20); 
        layout.setPadding(new Insets(40)); 
        layout.setAlignment(Pos.TOP_CENTER);
        GridPane form = new GridPane(); 
        form.setHgap(15); 
        form.setVgap(15); 
        form.setAlignment(Pos.CENTER);
        TextField txtQtd = new TextField();
        
        form.add(new Label("Beneficiário:"), 0, 0); 
        form.add(cbBeneficiarioEntrega, 1, 0);
        form.add(new Label("Item a Retirar:"), 0, 1); 
        form.add(cbItemEntrega, 1, 1);
        form.add(new Label("Quantidade / Volume:"), 0, 2); 
        form.add(txtQtd, 1, 2);

        Button btn = new Button("🚚 Confirmar Entrega");
        btn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setOnAction(e -> {
            try {
                validarCombo(cbBeneficiarioEntrega, "Família"); validarCombo(cbItemEntrega, "Item");
                Entrega ent = new Entrega(0, new java.sql.Date(System.currentTimeMillis()), Double.parseDouble(txtQtd.getText()), 
                                          cbBeneficiarioEntrega.getValue().getId(), cbItemEntrega.getValue().getId());
                entregaDAO.registrarEntrega(ent); 
                atualizarTudo(); 
                txtQtd.clear();
                mostrarAlerta("Sucesso", "Entrega realizada e estoque baixado!");
            } catch (Exception ex) { 
                mostrarAlerta("Erro", ex.getMessage()); 
            }
        });
        layout.getChildren().addAll(new Label("Controle de entregas"), form, btn);
        return layout;
    }

    private VBox criarPainelEmpresa() {
        VBox layout = new VBox(20); layout.setPadding(new Insets(30)); layout.setAlignment(Pos.TOP_CENTER);
        GridPane form = new GridPane(); form.setHgap(15); form.setVgap(10); form.setAlignment(Pos.CENTER);
        TextField txtN = new TextField(), txtC = new TextField(), txtT = new TextField(), txtE = new TextField();
        FormatadorUtil.aplicarMascaraCNPJ(txtC);

        form.add(new Label("Nome:"), 0, 0); form.add(txtN, 1, 0);
        form.add(new Label("CNPJ:"), 0, 1); form.add(txtC, 1, 1);
        form.add(new Label("Ramo:"), 2, 0); form.add(txtT, 3, 0);
        form.add(new Label("E-mail:"), 2, 1); form.add(txtE, 3, 1);
        form.add(new Label("Endereço:"), 0, 2); form.add(cbEndEmpresa, 1, 2);

        Button btn = new Button("Salvar Empresa");
        btn.setOnAction(e -> {
            try {
                validarCombo(cbEndEmpresa, "Endereço");
                empresaDAO.salvar(new Empresa(0, txtN.getText(), txtC.getText(), txtT.getText(), txtE.getText(), cbEndEmpresa.getValue().getId()));
                atualizarTudo(); 
                mostrarAlerta("Sucesso", "Empresa Cadastrada!");
            } catch (Exception ex) { 
                mostrarAlerta("Erro", ex.getMessage()); 
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Empresa"), form, btn);
        return layout;
    }

    private VBox criarPainelPatrocinio() {
        VBox layout = new VBox(20); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        GridPane form = new GridPane(); 
        form.setHgap(15); 
        form.setVgap(10); 
        form.setAlignment(Pos.CENTER);
        DatePicker dpI = new DatePicker(LocalDate.now()); 
        TextField txtV = new TextField();

        form.add(new Label("Empresa:"), 0, 0); 
        form.add(cbEmpresaPatrocinio, 1, 0);
        form.add(new Label("Campanha:"), 2, 0); 
        form.add(cbCampanhaPatrocinio, 3, 0);
        form.add(new Label("Início:"), 0, 1); 
        form.add(dpI, 1, 1);
        form.add(new Label("Valor:"), 2, 1); 
        form.add(txtV, 3, 1);

        Button btn = new Button("Ativar Patrocínio");
        btn.setOnAction(e -> {
            try {
                validarCombo(cbEmpresaPatrocinio, "Empresa"); validarCombo(cbCampanhaPatrocinio, "Campanha");
                Patrocinio p = new Patrocinio(0, java.sql.Date.valueOf(dpI.getValue()), null, "Contrato", Double.parseDouble(txtV.getText()), 
                                              cbCampanhaPatrocinio.getValue().getId(), cbEmpresaPatrocinio.getValue().getId());
                patrocinioDAO.salvar(p); 
                mostrarAlerta("Sucesso", "Patrocínio Ativo!");
            } catch (Exception ex) { 
                mostrarAlerta("Erro", ex.getMessage()); 
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Patrocínio"), form, btn);
        return layout;
    }

    private VBox criarPainelEndereco() {
        VBox layout = new VBox(20); 
        layout.setPadding(new Insets(40)); 
        layout.setAlignment(Pos.TOP_CENTER);
        GridPane form = new GridPane(); 
        form.setHgap(15); 
        form.setVgap(15); 
        form.setAlignment(Pos.CENTER);
        TextField txtR = new TextField(), txtN = new TextField(), txtB = new TextField(), txtC = new TextField();
        
        form.add(new Label("Rua:"), 0, 0); 
        form.add(txtR, 1, 0);
        form.add(new Label("Número:"), 2, 0); 
        form.add(txtN, 3, 0);
        form.add(new Label("Bairro:"), 0, 1); 
        form.add(txtB, 1, 1);
        form.add(new Label("Cidade:"), 2, 1); 
        form.add(txtC, 3, 1);

        Button btn = new Button("📍 Cadastrar Endereço");
        btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
        btn.setOnAction(e -> {
            try {
                enderecoDAO.salvar(new Endereco(0, txtR.getText(), txtN.getText(), txtB.getText(), txtC.getText()));
                atualizarTudo();
                mostrarAlerta("Sucesso", "Endereço pronto para uso!");
                txtR.clear(); 
                txtN.clear(); 
                txtB.clear(); 
                txtC.clear();
            } catch (Exception ex) { 
                mostrarAlerta("Erro", ex.getMessage()); 
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Endereço"), form, btn);
        return layout;
    }

    private VBox criarPainelOng() {
        VBox layout = new VBox(15); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        TextField txtN = new TextField(), txtC = new TextField(); 
        FormatadorUtil.aplicarMascaraCNPJ(txtC);
        GridPane g = new GridPane(); 
        g.setAlignment(Pos.CENTER); 
        g.setHgap(10); 
        g.setVgap(10);
        g.add(new Label("Nome:"),0,0); 
        g.add(txtN,1,0); 
        g.add(new Label("CNPJ:"),0,1); 
        g.add(txtC,1,1); 
        g.add(new Label("Endereço:"),0,2); 
        g.add(cbEndOng,1,2);
        Button b = new Button("Salvar ONG"); b.setOnAction(e -> {
            try { 
                ongDAO.salvar(new ONG(0, txtN.getText(), txtC.getText())); atualizarTudo(); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de ONG"), g, b); 
        return layout;
    }

    private VBox criarPainelDoador() {
        VBox layout = new VBox(15); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        TextField txtN = new TextField(), txtC = new TextField(); 
        FormatadorUtil.aplicarMascaraCPF(txtC);
        GridPane g = new GridPane(); 
        g.setAlignment(Pos.CENTER); 
        g.setHgap(10); 
        g.setVgap(10);
        g.add(new Label("Nome:"),0,0); 
        g.add(txtN,1,0); 
        g.add(new Label("CPF:"),0,1); 
        g.add(txtC,1,1); 
        g.add(new Label("Endereço:"),0,2); 
        g.add(cbEndDoador,1,2);
        Button b = new Button("Salvar Doador"); 
        b.setOnAction(e -> {
            try { doadorDAO.salvar(new Doador(0, txtN.getText(), txtC.getText())); 
                atualizarTudo(); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Doador"), g, b); 
        return layout;
    }

    private VBox criarPainelBeneficiario() {
        VBox layout = new VBox(15); layout.setPadding(new Insets(30)); layout.setAlignment(Pos.TOP_CENTER);
        TextField n = new TextField(), c = new TextField(), d = new TextField();
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER); 
        grid.setHgap(10); 
        grid.setVgap(10);
        grid.add(new Label("Nome:"),0,0); 
        grid.add(n,1,0); 
        grid.add(new Label("CPF:"),0,1); 
        grid.add(c,1,1); 
        grid.add(new Label("Dependentes:"),0,2); 
        grid.add(d,1,2); 
        grid.add(new Label("Endereço:"),0,3); 
        grid.add(cbEndBeneficiario,1,3);
        Button b = new Button("Salvar Beneficiário"); b.setOnAction(e -> {
            try { 
                beneficiarioDAO.salvar(new Beneficiario(0, n.getText(), c.getText(), Integer.parseInt(d.getText()))); atualizarTudo(); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Beneficiário"), grid, b); 
        return layout;
    }

    private VBox criarPainelVoluntario() {
        VBox layout = new VBox(15); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        TextField n = new TextField(), t = new TextField(), s = new TextField();
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER); 
        grid.setHgap(10); 
        grid.setVgap(10);
        grid.add(new Label("Nome:"),0,0); 
        grid.add(n,1,0); 
        grid.add(new Label("Telefone:"),0,1); 
        grid.add(t,1,1); 
        grid.add(new Label("Especialidade:"),0,2); 
        grid.add(s,1,2); 
        grid.add(new Label("Endereço:"),0,3); 
        grid.add(cbEndVoluntario,1,3);
        Button botao = new Button("Salvar Voluntário"); botao.setOnAction(e -> {
            try { 
                voluntarioDAO.salvar(new Voluntario(0, n.getText(), t.getText(), s.getText())); 
                atualizarTudo(); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Voluntário"), grid, botao); 
        return layout;
    }

    private VBox criarPainelCampanha() {
        VBox layout = new VBox(15); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        TextField n = new TextField(), c = new TextField();
        GridPane g = new GridPane(); 
        g.setAlignment(Pos.CENTER); 
        g.setHgap(10); 
        g.setVgap(10);
        g.add(new Label("Nome:"),0,0); 
        g.add(n,1,0); 
        g.add(new Label("Categoria:"),0,1); 
        g.add(c,1,1); g.add(new Label("ONG:"),0,2); 
        g.add(cbOngCampanha,1,2); 
        g.add(new Label("Endereço:"),0,3); 
        g.add(cbEndCampanha,1,3);
        Button b = new Button("Criar Campanha"); b.setOnAction(e -> {
            try { 
                campanhaDAO.salvar(new Campanha(0, n.getText(), new java.sql.Date(System.currentTimeMillis()), c.getText()), cbOngCampanha.getValue().getId(), cbEndCampanha.getValue().getId()); 
                atualizarTudo(); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Campanha"), g, b); 
        return layout;
    }

    private VBox criarPainelParticipacao() {
        VBox layout = new VBox(15); 
        layout.setPadding(new Insets(30)); 
        layout.setAlignment(Pos.TOP_CENTER);
        DatePicker d = new DatePicker(LocalDate.now()); 
        TextField h = new TextField(), j = new TextField();
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER); 
        grid.setHgap(10); 
        grid.setVgap(10);
        grid.add(new Label("Voluntário:"),0,0); 
        grid.add(cbVoluntarioParticipacao,1,0); 
        grid.add(new Label("Campanha:"),0,1); 
        grid.add(cbCampanhaParticipacao,1,1);
        grid.add(new Label("Data:"),0,2); 
        grid.add(d,1,2); 
        grid.add(new Label("Carga Horária:"),0,3); 
        grid.add(h,1,3); 
        grid.add(new Label("Cargo:"),0,4); 
        grid.add(j,1,4);
        Button b = new Button("Registrar Participação"); b.setOnAction(e -> {
            try { 
                participacaoDAO.salvar(new Participacao(0, java.sql.Date.valueOf(d.getValue()), null, h.getText(), j.getText(), cbCampanhaParticipacao.getValue().getId(), cbVoluntarioParticipacao.getValue().getId())); 
                mostrarAlerta("Sucesso", "Participação Salva!"); 
            } catch(Exception ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });
        layout.getChildren().addAll(new Label("Cadastro de Participação"), grid, b); 
        return layout;
    }

    private HBox criarPainelTelefones() {
        HBox master = new HBox(50); 
        master.setPadding(new Insets(40)); 
        master.setAlignment(Pos.CENTER);
        VBox v1 = new VBox(10); 
        TextField d1 = new TextField(), n1 = new TextField();
        v1.getChildren().addAll(new Label("Telefone ONG"), cbOngTelefone, new Label("DDD"), d1, new Label("Número"), n1, new Button("Salvar") {{
            setOnAction(e -> { 
                try { 
                    telefoneDAO.salvarTelOng(new TelefoneONG(0, n1.getText(), Integer.parseInt(d1.getText()), "Fixo", cbOngTelefone.getValue().getId())); 
                } catch(Exception ex) {
                    mostrarAlerta("Erro", ex.getMessage());
                }});
        }});
        VBox v2 = new VBox(10); 
        TextField d2 = new TextField(), n2 = new TextField();
        v2.getChildren().addAll(new Label("Telefone Doador"), cbDoadorTelefone, new Label("DDD"), d2, new Label("Número"), n2, new Button("Salvar") {{
            setOnAction(e -> { 
                try { 
                    telefoneDAO.salvarTelDoador(new TelefoneDoador(0, n2.getText(), Integer.parseInt(d2.getText()), "Celular", cbDoadorTelefone.getValue().getId())); 
                } catch(Exception ex) {
                    mostrarAlerta("Erro", ex.getMessage());
                }});
        }});
        master.getChildren().addAll(v1, new Separator(javafx.geometry.Orientation.VERTICAL), v2); 
        return master;
    }

    // --- LÓGICA DE APOIO ---

    private void atualizarTudo() {
        try {
            // Tabelas
            tabelaDoacoes.setItems(FXCollections.observableArrayList(doacaoDAO.listarDoacoes()));
            List<ItemEstoque> stock = estoqueDAO.listar();
            tabelaEstoque.setItems(FXCollections.observableArrayList(stock));
            lblTotal.setText(String.format("Total Arrecadado: %.2f Vol.", doacaoDAO.getSomaTotal()));

            // Dados das Listas
            ObservableList<Endereco> ends = FXCollections.observableArrayList(enderecoDAO.listar());
            ObservableList<Doador> doadores = FXCollections.observableArrayList(doadorDAO.listar());
            ObservableList<ONG> ongs = FXCollections.observableArrayList(ongDAO.listar());
            ObservableList<Campanha> camps = FXCollections.observableArrayList(campanhaDAO.listar());
            ObservableList<Empresa> emps = FXCollections.observableArrayList(empresaDAO.listar());
            ObservableList<Voluntario> vols = FXCollections.observableArrayList(voluntarioDAO.listar());
            ObservableList<Beneficiario> bens = FXCollections.observableArrayList(beneficiarioDAO.listar());

            // Atualizar Combos
            cbEndDoador.setItems(ends); 
            cbEndOng.setItems(ends); 
            cbEndCampanha.setItems(ends); 
            cbEndVoluntario.setItems(ends); 
            cbEndBeneficiario.setItems(ends); 
            cbEndEmpresa.setItems(ends);
            cbDoadorDoacao.setItems(doadores); 
            cbDoadorTelefone.setItems(doadores);
            cbOngCampanha.setItems(ongs); 
            cbOngEstoque.setItems(ongs); 
            cbOngTelefone.setItems(ongs);
            cbCampanhaDoacao.setItems(camps); 
            cbCampanhaPatrocinio.setItems(camps); 
            cbCampanhaParticipacao.setItems(camps);
            cbEmpresaPatrocinio.setItems(emps); 
            cbVoluntarioParticipacao.setItems(vols);
            cbBeneficiarioEntrega.setItems(bens); 
            cbItemEntrega.setItems(FXCollections.observableArrayList(stock));

        } catch (Exception ex) { System.out.println("Erro na atualização: " + ex.getMessage()); }
    }

    private void configurarTabelaDoacoes() {
        TableColumn<Doacao, String> colT = new TableColumn<>("Item"); 
        colT.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        TableColumn<Doacao, Double> colV = new TableColumn<>("Volume"); 
        colV.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tabelaDoacoes.getColumns().setAll(colT, colV);
        tabelaDoacoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void configurarTabelaEstoque() {
        TableColumn<ItemEstoque, String> colI = new TableColumn<>("Item"); 
        colI.setCellValueFactory(new PropertyValueFactory<>("nomeItem"));
        TableColumn<ItemEstoque, Double> colQ = new TableColumn<>("Qtd"); 
        colQ.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        TableColumn<ItemEstoque, String> colU = new TableColumn<>("Medida"); 
        colU.setCellValueFactory(new PropertyValueFactory<>("unidadeMedida"));
        tabelaEstoque.getColumns().setAll(colI, colQ, colU);
        tabelaEstoque.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void validarCombo(ComboBox<?> cb, String nome) throws Exception {
        if (cb.getValue() == null) throw new Exception("Selecione um(a) " + nome);
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(titulo.equals("Erro") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
        a.setTitle(titulo); 
        a.setHeaderText(null); 
        a.setContentText(msg); 
        a.showAndWait();
    }

    public static void main(String[] args) { launch(args); }
}