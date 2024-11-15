package IAmatriz;
import ClusteringCMeans.*;
import Modelos.CustomTableCellRenderer;
import Modelos.CustomTableCellRendererHelvetica;
import planoCart.CartesianPlane;
import planoCart.ColoredPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Interfaz {
    private JPanel contentPane;
    private JTextField txtFieldPointsQuantity;
    private JTextField txtFieldCentroidQuantity;
    private JButton btnAplicarPuntos;
    private JButton btnAplicarCentroides;
    private JTextField txtFieldParamFuzz;
    private JButton btnAplicarParamFuzz;
    private JTable tablePoints;
    private JScrollPane scPanePoints;
    private JButton btnStart;
    private JButton btnNextIt;
    private JTable tableCentroids;
    private JScrollPane scPaneCentroids;
    private JScrollPane scPaneBoolDistances;
    private JScrollPane scPaneBoolMemberships;
    private JScrollPane scPaneBoolCentroids;
    private JScrollPane scPaneBoolCosts;
    private JTable tbBoolDistances;
    private JTable tbBoolMemberships;
    private JTable tbBoolCentroids;
    private JTable tbBoolCosts;
    private JScrollPane scPaneFuzzDistances;
    private JScrollPane scPaneFuzzMemberships;
    private JScrollPane scPaneFuzzCentroids;
    private JScrollPane scPaneFuzzCosts;
    private JTable tbFuzzDistances;
    private JTable tbFuzzMemberships;
    private JTable tbFuzzCentroids;
    private JTable tbFuzzCosts;
    private JTextField BoolTotalCost;
    private JTextField FuzzTotalCost;
    private JButton btnModifValue;
    private JLabel labelIteration;
    private JPanel fillingPanelTitles;
    private JPanel graphPane;
    private JButton btnFuzzGraph;
    private JButton btnBoolGraph;
    private JSplitPane splitPanePrincipal;
    private JButton btnNormalGraph;
    //CMEANS CALCS
    //ALGORITM VARIABLES
    int pointsQuantity = 0;
    int centroidQuantity = 0;
    int iteration;
    ResultsCMeansBoolean resultsCMeansBoolean;
    ResultsCMeansFuzzy resultsCMeansFuzzy;
    //CMEANS MODELS
    PointsAndCentroidsTable pcTablesBoolean;
    PointsAndCentroidsTable pcTablesFuzzy;
    //MODELOS
    javax.swing.table.DefaultTableModel tabModelPoints;
    javax.swing.table.DefaultTableModel tabModelCents;
    javax.swing.table.DefaultTableModel tabModelDistances;
    javax.swing.table.DefaultTableModel tabModelMemberships;
    javax.swing.table.DefaultTableModel tabModelCentroids;
    javax.swing.table.DefaultTableModel tabModelCosts;
    javax.swing.table.DefaultTableModel tabModelFuzzDistances;
    javax.swing.table.DefaultTableModel tabModelFuzzMemberships;
    javax.swing.table.DefaultTableModel tabModelFuzzCentroids;
    javax.swing.table.DefaultTableModel tabModelFuzzCosts;


    public Interfaz() {
        JFrame frame = new JFrame("C-Means");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximizedBounds(frame.getBounds());
        frame.setMinimumSize(new java.awt.Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        frame.setJMenuBar(new javax.swing.JMenuBar());
        //frame.getJMenuBar().add(new javax.swing.JMenu("Ayuda")); //PIPIPI
        frame.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        initComponents();
        frame.pack();
        frame.setVisible(true);

        //Control Tables
        pcTablesBoolean = new PointsAndCentroidsTable(0, 0);
        pcTablesFuzzy = new PointsAndCentroidsTable(0, 0);
        System.out.println("Empieza");

        //GRAFICO
        graphPane.setLayout(null);
        planoCart.CartesianPlane plano = new planoCart.CartesianPlane();
        for(int i = 0; i < contentPane.getComponentCount(); i++){
            if(contentPane.getComponent(i) instanceof JSplitPane){
                graphPane.setSize(new Dimension(contentPane.getComponent(i).getSize().height, contentPane.getComponent(i).getSize().height));
                plano.setBounds(0, 0, graphPane.getWidth(), graphPane.getHeight());
            }
        }
        graphPane.add(plano);
        plano.setVisible(true);
        graphPane.repaint();
        graphPane.revalidate();
        System.out.println("Tamaño del planoCart: " + plano.getSize());


    }

    private void initComponents() {
        // No me dió timpo de implementar la validación de número decimales mientras se ingresan en la tabla UnU.
        fillingPanelTitles.setPreferredSize(new Dimension((int)(fillingPanelTitles.getParent().getSize().width / 2.80), 25));
        //TABLES INIT
        tabModelPoints = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0;
            }
        };
        tabModelCents = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0;
            }
        };
        tabModelDistances = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelMemberships = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelCentroids = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelCosts = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzDistances = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzMemberships = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzCentroids = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzCosts = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //TABLES SET UP
        tabModelPoints.addColumn("Punto");
        tabModelPoints.addColumn("X");
        tabModelPoints.addColumn("Y");
        tabModelCents.addColumn("Centroide");
        tabModelCents.addColumn("X");
        tabModelCents.addColumn("Y");
        //SCROLLPANE
        scPanePoints.setViewportView(tablePoints);
        tablePoints.setModel(tabModelPoints);
        scPaneCentroids.setViewportView(tableCentroids);
        tableCentroids.setModel(tabModelCents);

        scPaneBoolDistances.setViewportView(tbBoolDistances);
        tbBoolDistances.setModel(tabModelDistances);
        scPaneBoolMemberships.setViewportView(tbBoolMemberships);
        tbBoolMemberships.setModel(tabModelMemberships);
        scPaneBoolCentroids.setViewportView(tbBoolCentroids);
        tbBoolCentroids.setModel(tabModelCentroids);
        scPaneBoolCosts.setViewportView(tbBoolCosts);
        tbBoolCosts.setModel(tabModelCosts);

        scPaneFuzzDistances.setViewportView(tbFuzzDistances);
        tbFuzzDistances.setModel(tabModelFuzzDistances);
        scPaneFuzzMemberships.setViewportView(tbFuzzMemberships);
        tbFuzzMemberships.setModel(tabModelFuzzMemberships);
        scPaneFuzzCentroids.setViewportView(tbFuzzCentroids);
        tbFuzzCentroids.setModel(tabModelFuzzCentroids);
        scPaneFuzzCosts.setViewportView(tbFuzzCosts);
        tbFuzzCosts.setModel(tabModelFuzzCosts);

        for (int i = 0; i < tablePoints.getColumnCount(); i++) {
            tablePoints.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRenderer());
            tablePoints.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }
        for (int i = 0; i < tableCentroids.getColumnCount(); i++) {
            tableCentroids.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRenderer());
            tableCentroids.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());

            /*tbBoolCentroids.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRenderer());
            tbBoolCentroids.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());

            tbFuzzCentroids.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRenderer());
            tbFuzzCentroids.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());*/
        }

        tableCentroids.setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        tableCentroids.setShowVerticalLines(true);
        tablePoints.setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        tablePoints.setShowVerticalLines(true);
        //REORDERING SET UP
        tablePoints.getTableHeader().setReorderingAllowed(false);
        tableCentroids.getTableHeader().setReorderingAllowed(false);
        tbBoolDistances.getTableHeader().setReorderingAllowed(false);
        tbBoolMemberships.getTableHeader().setReorderingAllowed(false);
        tbBoolCentroids.getTableHeader().setReorderingAllowed(false);
        tbBoolCosts.getTableHeader().setReorderingAllowed(false);
        tbFuzzDistances.getTableHeader().setReorderingAllowed(false);
        tbFuzzMemberships.getTableHeader().setReorderingAllowed(false);
        tbFuzzCentroids.getTableHeader().setReorderingAllowed(false);
        tbFuzzCosts.getTableHeader().setReorderingAllowed(false);

        //NON EDITABLE COLUMNS
        tablePoints.getColumnModel().getColumn(0).setCellEditor(null);
        tableCentroids.getColumnModel().getColumn(0).setCellEditor(null);

        //TXT_FIELDS LISTENERS-------------------------------------------------
        txtFieldPointsQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtFieldPointsQuantityKeyTyped(e);
            }
        });
        txtFieldCentroidQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                setTxtFieldCentroidQuantityKeyTyped(e);
            }
        });

        txtFieldParamFuzz.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtFieldParamFuzzKeyTyped(e);
            }
        });

        //BUTTONS LISTENERS---------------------------------------------------
        btnAplicarPuntos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAplicarPuntosActionListener(e);
            }
        });

        btnAplicarCentroides.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAplicarCentroidesActionListener(e);
            }
        });

        btnAplicarParamFuzz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAplicarParamFuzzActionListener(e);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartActionListener(e);
            }
        });

        btnModifValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnModifValueActionListener(e);
            }
        });

        btnNextIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNextItActionListener(e);
            }
        });
        btnBoolGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBoolGraphActionListener(e);
            }
        });
        btnFuzzGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFuzzGraphActionListener(e);
            }
        });
        btnNormalGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNormalGraphActionListener(e);
            }
        });
    }

    private void txtFieldPointsQuantityKeyTyped(java.awt.event.KeyEvent e){
        int key = e.getKeyChar();
        if(!(Character.isDigit(key))){
            e.consume();
        }
        if(txtFieldPointsQuantity.getText().length() >= 3){
            e.consume();
        }
    }
    private void setTxtFieldCentroidQuantityKeyTyped(java.awt.event.KeyEvent e){
        int key = e.getKeyChar();

        if(!(Character.isDigit(key))){
            e.consume();
        }
        if(txtFieldCentroidQuantity.getText().length() >= 3){
            e.consume();
        }
    }
    private void txtFieldParamFuzzKeyTyped(java.awt.event.KeyEvent e){
        //Este si lo pude validarrrrrr, es q un txtField es más fácilxd admite únicamente decimales y no tiene posibilidad de error(segunyo)
        int key = e.getKeyChar();
        int count = 0;
        for(int i = 0; i < txtFieldParamFuzz.getText().length(); i++)
            if(txtFieldParamFuzz.getText().charAt(i) == '.')
                count++;
        if (!((key >= 48 && key <= 57) || key == '.') || (key == '.' && count >= 1))
            e.consume();
        if(txtFieldParamFuzz.getText().isEmpty() && key == '.')
            e.consume();
        if(txtFieldParamFuzz.getText().contains(".")){
            if(txtFieldParamFuzz.getText().substring(txtFieldParamFuzz.getText().indexOf('.'), txtFieldParamFuzz.getText().length()).length() >= 5)
                e.consume();
        }
        else{
            if(txtFieldParamFuzz.getText().length() >= 1 && key != '.')
                e.consume();
        }
        if(txtFieldParamFuzz.getText().equals(String.valueOf(2))){
            e.consume();
        }
    }
    private void btnAplicarPuntosActionListener(java.awt.event.ActionEvent e){
        //VERIF
        if(txtFieldPointsQuantity.getText().isEmpty() || txtFieldPointsQuantity.getText().isBlank() || txtFieldPointsQuantity.getText() == null || Integer.parseInt(txtFieldPointsQuantity.getText()) <= 0){
            JOptionPane.showMessageDialog(null, "La cantidad de puntos debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Pa q no se cuelgue pq si ando editando una celda a eliminar tira excepciones
        tablePoints.removeEditor();

        if(pointsQuantity == 0){
            while(tabModelPoints.getRowCount() > 0){
                tabModelPoints.removeRow(0);
            }
            pointsQuantity = Integer.parseInt(txtFieldPointsQuantity.getText());
            for(int i = 0; i < pointsQuantity; i++){
                tabModelPoints.addRow(new Object[]{"P"+(i+1), null, null});
            }
            tablePoints.setPreferredSize(new java.awt.Dimension(tablePoints.getPreferredSize().width, 20*pointsQuantity));
            scPanePoints.setViewportView(tablePoints);
        }else{
            pointsQuantity = Integer.parseInt(txtFieldPointsQuantity.getText());
            while(tabModelPoints.getRowCount() > pointsQuantity){
                tabModelPoints.removeRow(pointsQuantity);
            }
            int actRowCount = tabModelPoints.getRowCount();
            for(int i = actRowCount; i < pointsQuantity; i++){
                tabModelPoints.addRow(new Object[]{"P"+(i+1), null, null});
            }
            tablePoints.setPreferredSize(new java.awt.Dimension(tablePoints.getPreferredSize().width, 20*pointsQuantity));
            scPanePoints.setViewportView(tablePoints);
        }

    }
    private void btnAplicarCentroidesActionListener(java.awt.event.ActionEvent e){
        //VERIF //Creo q no va a haber verif necesaria
        if(txtFieldCentroidQuantity.getText().isEmpty() || txtFieldCentroidQuantity.getText().isBlank() || txtFieldCentroidQuantity.getText() == null || Integer.parseInt(txtFieldCentroidQuantity.getText()) <= 0){
            JOptionPane.showMessageDialog(null, "La cantidad de centroides debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
        }
        tableCentroids.removeEditor();

        if(centroidQuantity == 0){
            while(tabModelCents.getRowCount() > 0){
                tabModelCents.removeRow(0);
            }
            centroidQuantity = Integer.parseInt(txtFieldCentroidQuantity.getText());
            for(int i = 0; i < centroidQuantity; i++){
                tabModelCents.addRow(new Object[]{"C"+(i+1), null, null});
            }
            tableCentroids.setPreferredSize(new java.awt.Dimension(tableCentroids.getPreferredSize().width, 20*centroidQuantity));
            scPaneCentroids.setViewportView(tableCentroids);
        }else{
            centroidQuantity = Integer.parseInt(txtFieldCentroidQuantity.getText());
            while(tabModelCents.getRowCount() > centroidQuantity){
                tabModelCents.removeRow(centroidQuantity);
            }
            int actRowCount = tabModelCents.getRowCount();
            for(int i = actRowCount; i < centroidQuantity; i++){
                tabModelCents.addRow(new Object[]{"C"+(i+1), null, null});
            }
            tableCentroids.setPreferredSize(new java.awt.Dimension(tableCentroids.getPreferredSize().width, 20*centroidQuantity));
            scPaneCentroids.setViewportView(tableCentroids);
        }

    }
    private void btnAplicarParamFuzzActionListener(java.awt.event.ActionEvent e){
        //VERIF
        if(txtFieldParamFuzz.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "El campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        float paramFuzz = Float.parseFloat(txtFieldParamFuzz.getText());
        pcTablesBoolean.setFuzzyParam(paramFuzz);
        System.out.println("FUZZY PARAM: "+ pcTablesBoolean.getFuzzyParam());
    }
    private void btnStartActionListener(java.awt.event.ActionEvent e){
        //VERIF
        if(!generalValidation()){
            return;
        }
        iteration = 1;
        labelIteration.setText("Iteración: "+ iteration);
        btnStart.setEnabled(false);
        btnModifValue.setEnabled(true);
        btnNextIt.setEnabled(true);
        txtFieldParamFuzz.setEnabled(false);
        txtFieldCentroidQuantity.setEnabled(false);
        txtFieldPointsQuantity.setEnabled(false);
        pcTablesFuzzy.setFuzzyParam(pcTablesBoolean.getFuzzyParam());
        pcTablesFuzzy.setPoints(pcTablesBoolean.getPoints());
        pcTablesFuzzy.setCentroids(pcTablesBoolean.getCentroids());
        tablePoints.setEnabled(false);
        tableCentroids.setEnabled(false);
        btnFuzzGraph.setEnabled(true);
        btnBoolGraph.setEnabled(true);
        System.out.println("VALIDACION CORRECTA");

        //ALGORITHM START
        CMeansBoolean cmeansB = new CMeansBoolean() {
            @Override
            public ResultsCMeansBoolean getResultsCMeansBoolean(PointsAndCentroidsTable pointsAndCentroidsTable) {
                return super.getResultsCMeansBoolean(pointsAndCentroidsTable);
            }
        };
        resultsCMeansBoolean = cmeansB.getResultsCMeansBoolean(pcTablesBoolean);
        CMeansFuzzy cmeansF = new CMeansFuzzy() {
            @Override
            public ResultsCMeansFuzzy getResultsCMeansFuzzy(PointsAndCentroidsTable pointsAndCentroidsTable) {
                return super.getResultsCMeansFuzzy(pointsAndCentroidsTable);
            }
        };
        resultsCMeansFuzzy = cmeansF.getResultsCMeansFuzzy(pcTablesFuzzy);

        //TABLES UPDATE
        fillTablesBoolean();
        fillTablesFuzzy();


    }
    private void btnModifValueActionListener(java.awt.event.ActionEvent e){
        int op = JOptionPane.showConfirmDialog(null, "El iterador será reiniciado y se detendrán el algoritmo\n¿Desea modificar los valores?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(op == JOptionPane.YES_OPTION){
            iteration = 1;
            labelIteration.setText("Iteración: --");
            btnStart.setEnabled(true);
            btnModifValue.setEnabled(false);
            btnNextIt.setEnabled(false);
            tablePoints.setEnabled(true);
            tableCentroids.setEnabled(true);
            txtFieldParamFuzz.setEnabled(true);
            txtFieldCentroidQuantity.setEnabled(true);
            txtFieldPointsQuantity.setEnabled(true);
            btnBoolGraph.setEnabled(false);
            btnFuzzGraph.setEnabled(false);
            pcTablesBoolean = new PointsAndCentroidsTable(0, 0);
            pcTablesFuzzy = new PointsAndCentroidsTable(0, 0);
            System.out.println("ALGORITMO DETENIDO");
        }
    }
    private void btnNextItActionListener(java.awt.event.ActionEvent e) {
        iteration++;
        labelIteration.setText("Iteración: "+ iteration);
        CMeansBoolean cmeansB = new CMeansBoolean() {
            @Override
            public ResultsCMeansBoolean getResultsCMeansBoolean(PointsAndCentroidsTable pointsAndCentroidsTable) {
                return super.getResultsCMeansBoolean(pointsAndCentroidsTable);
            }
        };
        resultsCMeansBoolean = cmeansB.getResultsCMeansBoolean(pcTablesBoolean);
        CMeansFuzzy cmeansF = new CMeansFuzzy() {
            @Override
            public ResultsCMeansFuzzy getResultsCMeansFuzzy(PointsAndCentroidsTable pointsAndCentroidsTable) {
                return super.getResultsCMeansFuzzy(pointsAndCentroidsTable);
            }

            ;
        };
        resultsCMeansFuzzy = cmeansF.getResultsCMeansFuzzy(pcTablesFuzzy);

        //TABLES UPDATE
        fillTablesBoolean();
        fillTablesFuzzy();
    }
    // 1 - Tabla Correcta
    // 2 - Celdas vacías
    // 3 - Valores invalidos
    // 4 - Tamaño invalido
    private boolean generalValidation(){
        switch (verifPointsTable()){
            case 2:
                JOptionPane.showMessageDialog(null, "Celdas vacías\nLlenar las celdas", "Error - Tabla de puntos", JOptionPane.ERROR_MESSAGE);
                return false;
            case 3:
                JOptionPane.showMessageDialog(null, "Valores invalidos\nRevisar valores ingresados", "Error - Tabla de puntos", JOptionPane.ERROR_MESSAGE);
                return false;
            case 4:
                JOptionPane.showMessageDialog(null, "Tamaño invalido\nRevisar cantidad de puntos", "Error - Tabla de puntos", JOptionPane.ERROR_MESSAGE);
                return false;
            default:
                break;
        }
        switch (verifCentroidTable()){
            case 2:
                JOptionPane.showMessageDialog(null, "Celdas vacías\nLlenar las celdas", "Error - Tabla de centroides", JOptionPane.ERROR_MESSAGE);
                return false;
            case 3:
                JOptionPane.showMessageDialog(null, "Valores invalidos\nRevisar valores ingresados", "Error - Tabla de centroides", JOptionPane.ERROR_MESSAGE);
                return false;
            case 4:
                JOptionPane.showMessageDialog(null, "Tamaño invalido\nRevisar cantidad de puntos", "Error - Tabla de centroides", JOptionPane.ERROR_MESSAGE);
                return false;
            default:
                break;
        }
        if(pcTablesBoolean.getFuzzyParam() == -1) {
            JOptionPane.showMessageDialog(null, "Parametro de fuzzificación vacío\nRevisar valor", "Error - Parametro de Fuzzificación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!pcTablesBoolean.validation()){
            JOptionPane.showMessageDialog(null, "Error al guardar los datos", "Error GENERAL", JOptionPane.ERROR_MESSAGE);
            System.out.println("Falló la validación de cada dato, revisar asignaciones y demás, aydio unu");
            return false;
        }
        return true;
    }

    private void btnBoolGraphActionListener(ActionEvent e){
        System.out.println("Boton Graficar Bool");
        while(graphPane.getComponentCount() > 0){
            graphPane.remove(0);
        }
        CartesianPlane plane = new CartesianPlane();
        for(int i = 0; i < contentPane.getComponentCount(); i++){
            if(contentPane.getComponent(i) instanceof JSplitPane){
                graphPane.setSize(new Dimension(contentPane.getComponent(i).getSize().height, contentPane.getComponent(i).getSize().height));
                plane.setBounds(0, 0, graphPane.getWidth(), graphPane.getHeight());
            }
        }
        graphPane.add(plane);
        plane.setVisible(true);
        for(int i = 0; i < pcTablesBoolean.getPointsQuantity(); i++){
            System.out.println("Punto enviado -> "+pcTablesBoolean.getPoints()[i][0]+", "+pcTablesBoolean.getPoints()[i][1]);
            plane.addPoint(new ColoredPoint(pcTablesBoolean.getPoints()[i][0], pcTablesBoolean.getPoints()[i][1], Color.GREEN));
        }
        for(int i = 0; i < resultsCMeansBoolean.getNewCentroids().length; i++){
            System.out.println("Centroide enviado -> "+resultsCMeansBoolean.getNewCentroids()[i][0]+", "+resultsCMeansBoolean.getNewCentroids()[i][1]);
            plane.addPoint(new ColoredPoint(resultsCMeansBoolean.getNewCentroids()[i][0], resultsCMeansBoolean.getNewCentroids()[i][1], Color.RED));
        }
        graphPane.repaint();
        graphPane.revalidate();
    }

    private void btnFuzzGraphActionListener(ActionEvent e){
        System.out.println("Boton Graficar Fuzz");
        graphPane.remove(0);
        CartesianPlane plane = new CartesianPlane();
        for(int i = 0; i < contentPane.getComponentCount(); i++){
            if(contentPane.getComponent(i) instanceof JSplitPane){
                graphPane.setSize(new Dimension(contentPane.getComponent(i).getSize().height, contentPane.getComponent(i).getSize().height));
                plane.setBounds(0, 0, graphPane.getWidth(), graphPane.getHeight());
            }
        }
        graphPane.add(plane);
        plane.setVisible(true);
        for(int i = 0; i < pcTablesBoolean.getPointsQuantity(); i++){
            System.out.println("Punto enviado -> "+pcTablesBoolean.getPoints()[i][0]+", "+pcTablesBoolean.getPoints()[i][1]);
            plane.addPoint(new ColoredPoint(pcTablesBoolean.getPoints()[i][0], pcTablesBoolean.getPoints()[i][1], Color.GREEN));
        }
        for(int i = 0; i < resultsCMeansFuzzy.getNewCentroids().length; i++){
            System.out.println("Centroide enviado -> "+resultsCMeansFuzzy.getNewCentroids()[i][0]+", "+resultsCMeansFuzzy.getNewCentroids()[i][1]);
            plane.addPoint(new ColoredPoint(resultsCMeansFuzzy.getNewCentroids()[i][0], resultsCMeansFuzzy.getNewCentroids()[i][1], Color.RED));
        }
        graphPane.repaint();
        graphPane.revalidate();
    }

    private void btnNormalGraphActionListener(ActionEvent e){
        System.out.println("Boton Graficar Normal");
        if(!generalValidation()){
            return;
        }
        graphPane.remove(0);
        CartesianPlane plane = new CartesianPlane();
        for(int i = 0; i < contentPane.getComponentCount(); i++){
            if(contentPane.getComponent(i) instanceof JSplitPane){
                graphPane.setSize(new Dimension(contentPane.getComponent(i).getSize().height, contentPane.getComponent(i).getSize().height));
                plane.setBounds(0, 0, graphPane.getWidth(), graphPane.getHeight());
            }
        }
        graphPane.add(plane);
        plane.setVisible(true);
        for(int i = 0; i < pointsQuantity; i++){
            plane.addPoint(new ColoredPoint(Float.valueOf(tabModelPoints.getValueAt(i, 1).toString()), Float.valueOf(tabModelPoints.getValueAt(i, 2).toString()), Color.GREEN));
        }
        for(int i = 0; i < centroidQuantity; i++){
            plane.addPoint(new ColoredPoint(Float.valueOf(tabModelCents.getValueAt(i, 1).toString()), Float.valueOf(tabModelCents.getValueAt(i, 2).toString()), Color.RED));
        }
        graphPane.repaint();
        graphPane.revalidate();
    }

    private int verifPointsTable(){
        if(tabModelPoints.getRowCount() == 0 || tabModelCents.getRowCount() > 999){
            return 4;
        }
        float[][] points = new float[tabModelPoints.getRowCount()][2];
        for(int i = 0; i < tabModelPoints.getRowCount(); i++){
            if(tabModelPoints.getValueAt(i, 1) == null || tabModelPoints.getValueAt(i, 2) == null){
                return 2;
            }
            if(tabModelPoints.getValueAt(i, 1).toString().isBlank() || tabModelPoints.getValueAt(i, 2).toString().isBlank()){
                return 2;
            }
            try {
                points[i][0] = Float.parseFloat(tabModelPoints.getValueAt(i, 1).toString());
                points[i][1] = Float.parseFloat(tabModelPoints.getValueAt(i, 2).toString());
            } catch (NumberFormatException ex){
                return 3;
            }
        }
        this.pcTablesBoolean.setPoints(points);
        return 1;
    }

    private int verifCentroidTable(){
        if(tabModelCents.getRowCount() == 0 || tabModelCents.getRowCount() > 999){
            return 4;
        }
        float[][] centroids = new float[tabModelCents.getRowCount()][2];
        for(int i = 0; i < tabModelCents.getRowCount(); i++){
            if(tabModelCents.getValueAt(i, 1) == null || tabModelCents.getValueAt(i, 2) == null){
                return 2;
            }
            if(tabModelCents.getValueAt(i, 1).toString().isBlank() || tabModelCents.getValueAt(i, 2).toString().isBlank()){
                return 2;
            }
            try {
                centroids[i][0] = Float.parseFloat(tabModelCents.getValueAt(i, 1).toString());
                centroids[i][1] = Float.parseFloat(tabModelCents.getValueAt(i, 2).toString());
            } catch (NumberFormatException ex){
                return 3;
            }
        }
        this.pcTablesBoolean.setCentroids(centroids);
        return 1;
    }

    private void fillTablesBoolean(){
        float[][] dists = resultsCMeansBoolean.getDistances();
        boolean[][] memb = resultsCMeansBoolean.getMembership();
        float[][] cents = resultsCMeansBoolean.getNewCentroids();
        float[] costs = resultsCMeansBoolean.getCost();
        float totalCost = resultsCMeansBoolean.getTotalCost();

        //CLEAR TABLES
        tabModelDistances = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelMemberships = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelCentroids = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelCosts = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbBoolDistances.setModel(tabModelDistances);
        tbBoolMemberships.setModel(tabModelMemberships);
        tbBoolCentroids.setModel(tabModelCentroids);
        tbBoolCosts.setModel(tabModelCosts);

        tabModelDistances.addColumn(" ");
        tabModelMemberships.addColumn(" ");
        for(int i = 1; i <= dists[0].length; i++){
            tabModelDistances.addColumn("P"+(i));
            tabModelMemberships.addColumn("P"+(i));
        }

        for(int i = 0; i < dists.length; i++){
            Object[] rowDistance = new Object[dists[0].length+1];
            Object[] rowMembership = new Object[dists[0].length+1];
            rowDistance[0] = "C"+(i+1);
            rowMembership[0] = "C"+(i+1);
            for(int j = 0; j < dists[0].length; j++){
                rowDistance[j+1] = String.format("%.4f", dists[i][j]);
                rowMembership[j+1] = memb[i][j] ? 1 : 0;

            }
            tabModelDistances.addRow(rowDistance);
            tabModelMemberships.addRow(rowMembership);
        }
        tbBoolDistances.setPreferredSize(new java.awt.Dimension(dists[0].length*80, 20*(dists.length+1)));
        tbBoolMemberships.setPreferredSize(new java.awt.Dimension(dists[0].length*80, 20*(dists.length+1)));

        tabModelCentroids.addColumn("C");
        tabModelCentroids.addColumn("X");
        tabModelCentroids.addColumn("Y");
        tabModelCosts.addColumn("Costo");
        tabModelCosts.addColumn("");
        for(int i = 0; i < cents.length; i++){
            tabModelCentroids.addRow(new Object[]{"C"+(i+1), String.format("%.4f", cents[i][0]), String.format("%.4f", cents[i][1])});
            tabModelCosts.addRow(new Object[]{"C"+(i+1), String.format("%.4f", costs[i])});
        }
        tbBoolCentroids.setPreferredSize(new java.awt.Dimension(3*70, 20*(cents.length+1)));
        tbBoolCosts.setPreferredSize(new java.awt.Dimension(3*70, 20*(cents.length+1)));
        BoolTotalCost.setText(String.format("%.4f", totalCost));
        pcTablesBoolean.setCentroids(cents);

        for (int i = 0; i < tbBoolDistances.getColumnCount(); i++) {
            tbBoolDistances.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
            tbBoolMemberships.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
        for(int i = 0; i < tbBoolCentroids.getColumnCount(); i++){
            tbBoolCentroids.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
        for(int i = 0; i < tbBoolCosts.getColumnCount(); i++){
            tbBoolCosts.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
    }

    private void fillTablesFuzzy() {
        float[][] dists = resultsCMeansFuzzy.getDistances();
        float[][] memb = resultsCMeansFuzzy.getMemberships();
        float[][] cents = resultsCMeansFuzzy.getNewCentroids();
        float[] costs = resultsCMeansFuzzy.getCost();
        float totalCost = resultsCMeansFuzzy.getTotalCost();

        tabModelFuzzDistances = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzMemberships = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzCentroids = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabModelFuzzCosts = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbFuzzDistances.setModel(tabModelFuzzDistances);
        tbFuzzMemberships.setModel(tabModelFuzzMemberships);
        tbFuzzCentroids.setModel(tabModelFuzzCentroids);
        tbFuzzCosts.setModel(tabModelFuzzCosts);

        tabModelFuzzDistances.addColumn(" ");
        tabModelFuzzMemberships.addColumn(" ");
        for (int i = 1; i <= dists[0].length; i++) {
            tabModelFuzzDistances.addColumn("P" + (i));
            tabModelFuzzMemberships.addColumn("P" + (i));
        }

        for(int i = 0; i < dists.length; i++){
            Object[] rowDistance = new Object[dists[0].length+1];
            Object[] rowMembership = new Object[dists[0].length+1];
            rowDistance[0] = "C"+(i+1);
            rowMembership[0] = "C"+(i+1);
            for(int j = 0; j < dists[0].length; j++){
                rowDistance[j+1] = String.format("%.4f", dists[i][j]);
                rowMembership[j+1] = String.format("%.4f", memb[i][j]);
            }
            tabModelFuzzDistances.addRow(rowDistance);
            tabModelFuzzMemberships.addRow(rowMembership);
        }
        tbFuzzDistances.setPreferredSize(new java.awt.Dimension(dists[0].length*80, 20*(dists.length+1)));
        tbFuzzMemberships.setPreferredSize(new java.awt.Dimension(dists[0].length*80, 20*(dists.length+1)));

        tabModelFuzzCentroids.addColumn("C");
        tabModelFuzzCentroids.addColumn("X");
        tabModelFuzzCentroids.addColumn("Y");
        tabModelFuzzCosts.addColumn("Costo");
        tabModelFuzzCosts.addColumn("");
        for(int i = 0; i < cents.length; i++){
            tabModelFuzzCentroids.addRow(new Object[]{"C"+(i+1), String.format("%.4f", cents[i][0]), String.format("%.4f", cents[i][1])});
            tabModelFuzzCosts.addRow(new Object[]{"C"+(i+1), String.format("%.4f", costs[i])});
        }
        tbFuzzCentroids.setPreferredSize(new java.awt.Dimension(3*70, 20*(cents.length+1)));
        tbFuzzCosts.setPreferredSize(new java.awt.Dimension(3*70, 20*(cents.length+1)));
        FuzzTotalCost.setText(String.format("%.4f", totalCost));
        pcTablesFuzzy.setCentroids(cents);

        for (int i = 0; i < tbFuzzDistances.getColumnCount(); i++) {
            tbFuzzDistances.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
            tbFuzzMemberships.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
        for(int i = 0; i < tbFuzzCentroids.getColumnCount(); i++){
            tbFuzzCentroids.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
        for(int i = 0; i < tbFuzzCosts.getColumnCount(); i++){
            tbFuzzCosts.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableCellRendererHelvetica());
        }
    }

    public static void main(String[] args) {
        com.formdev.flatlaf.FlatLightLaf.setup();
        UIManager.put("Button.arc", 10);
        //UIManager.put("Table.gridColor", new java.awt.Color(0, 0, 0));
        Interfaz frame = new Interfaz();

    }
}
