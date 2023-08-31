Public Class form_options

    Private allowPause As Boolean

    Private disableRandom As Boolean

    Private time As Integer

    Private Sub form_options_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim param As Paramètres = GameStorage.getParamètre()
        allowPause = param.allowPause
        disableRandom = param.disableRandom
        time = param.allowedTime

        CheckBoxPause.Checked = allowPause
        CheckBoxCartesAleatoire.Checked = disableRandom
        updateLabels()
    End Sub

    Private Sub updateLabels()
        labelTimer.Text = GameUtils.secsToStr(time, "mm:ss")

        If time >= 180 Then
            ButtonIncrementTime.Enabled = False
        ElseIf time <= 0 Then
            ButtonDecrementTime.Enabled = False
            labelTimer.Text = "Aucune limite de temps"
            labelTimer.Enabled = False
        Else
            ButtonDecrementTime.Enabled = True
            ButtonIncrementTime.Enabled = True
            labelTimer.Enabled = True
        End If

        CheckBoxCartesAleatoire.Text = IIf(disableRandom, "cartes non mélangées", "cartes mélangées")
        CheckBoxPause.Text = IIf(allowPause, "Pause autorisée", "Pause interdite")
    End Sub
    Private Sub ButtonIncrementTime_Click(sender As Object, e As EventArgs) Handles ButtonIncrementTime.Click
        If time < 180 Then
            time += 15
        End If
        updateLabels()
    End Sub
    Private Sub ButtonDecrementTime_Click(sender As Object, e As EventArgs) Handles ButtonDecrementTime.Click
        If time > 0 Then
            time -= 15
        End If
        updateLabels()
    End Sub

    Private Sub CheckBoxCartesAleatoire_CheckedChanged(sender As Object, e As EventArgs) Handles CheckBoxCartesAleatoire.CheckedChanged
        disableRandom = CheckBoxCartesAleatoire.Checked
        updateLabels()
    End Sub
    Private Sub CheckBox1_CheckedChanged(sender As Object, e As EventArgs) Handles CheckBoxPause.CheckedChanged
        allowPause = CheckBoxPause.Checked
        updateLabels()
    End Sub

    Private Sub buttonValidation_Click(sender As Object, e As EventArgs) Handles buttonValidation.Click
        Dim newParamètre As Paramètres
        newParamètre.allowPause = allowPause
        newParamètre.disableRandom = disableRandom
        newParamètre.allowedTime = time

        GameStorage.setParamètre(newParamètre)
        GameStorage.Sauvegarder()

        Me.Hide()
        form_home.Show()
    End Sub

    Private Sub btn_abort_Click(sender As Object, e As EventArgs) Handles btn_abort.Click
        Me.Hide()
        form_home.Show()
    End Sub
End Class