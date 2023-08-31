Public Class form_home
    ''' <summary>
    ''' Initialisation du formulaire
    ''' </summary>
    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ComboBox_name.DropDownStyle = ComboBoxStyle.DropDown
        GameStorage.init()

        For Each nom As String In GameStorage.listeNoms()
            ComboBox_name.Items.Add(nom)
        Next
    End Sub

    ''' <summary>
    ''' Démarrage de partie. Assume que le nom est valide.
    ''' </summary>
    Private Sub btn_play_Click(sender As Object, e As EventArgs) Handles btn_play.Click
        GameStorage.setPlayerName(ComboBox_name.Text)

        Me.Hide()
        Dim newGame As New form_game()
        newGame.Show()
    End Sub


    ' Vérification de la validité du nom
    Private Const MIN_NAME_LENGTH As Integer = 3
    Private Const NAME_TOO_SHORT As String = "Un nom de plus de trois caractères est requis !"
    Private Const UNRECONIZED_NAME As String = "Au démarrage, un nouveau profil nommé NAME sera créé"
    Private Const RECONIZED_NAME As String = "Bienvenue, NAME ! Vous êtes prêt à démarrer"
    Private Sub onNameChange(sender As Object, e As EventArgs) Handles ComboBox_name.TextUpdate, ComboBox_name.SelectedIndexChanged
        Dim name = Trim(ComboBox_name.Text)
        Dim nameValid = name.Length >= MIN_NAME_LENGTH

        btn_play.Enabled = nameValid
        If (nameValid) Then
            If (ComboBox_name.Items.Contains(name)) Then
                lbl_nameValid.Text = RECONIZED_NAME.Replace("NAME", name)
                lbl_nameValid.ForeColor = Color.ForestGreen
            Else
                lbl_nameValid.Text = UNRECONIZED_NAME.Replace("NAME", name)
                lbl_nameValid.ForeColor = Color.OrangeRed
            End If
        Else
            lbl_nameValid.Text = NAME_TOO_SHORT
            lbl_nameValid.ForeColor = Color.DarkRed
        End If
    End Sub

    Private Sub btn_option_Click(sender As Object, e As EventArgs) Handles btn_option.Click
        Dim dialog As New form_options
        dialog.ShowDialog()
    End Sub

    Private Sub btn_score_Click(sender As Object, e As EventArgs) Handles btn_score.Click
        Dim dialog As New form_scores
        dialog.ShowDialog()
    End Sub

    ' Confirmation de fermeture : 
    Private Const CLOSE_CONFIRM_MSG = "Voulez-vous vraiment quitter l'application ?" & vbCrLf &
                   "(La réponse est évidemment oui mais le cahier des charge veut vous forcer à rester ici...)"
    Private Sub ConfirmClose()
        If confirm(CLOSE_CONFIRM_MSG) Then
            Application.Exit()
        End If
    End Sub
    Private Sub ConfirmClose_native(ByVal sender As System.Object, ByVal e As System.ComponentModel.CancelEventArgs) Handles MyClass.Closing
        ' Au clic du bouton rouge natif de fermeture
        ConfirmClose()
        e.Cancel = True
    End Sub
    Private Sub ConfirmClose_btn(sender As Object, e As EventArgs) Handles btn_exit.Click
        ' Au clic du bouton "quitter"
        ConfirmClose()
    End Sub
End Class
