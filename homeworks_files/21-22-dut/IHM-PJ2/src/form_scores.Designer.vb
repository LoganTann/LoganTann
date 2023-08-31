<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class form_scores
    Inherits System.Windows.Forms.Form

    'Form remplace la méthode Dispose pour nettoyer la liste des composants.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Requise par le Concepteur Windows Form
    Private components As System.ComponentModel.IContainer

    'REMARQUE : la procédure suivante est requise par le Concepteur Windows Form
    'Elle peut être modifiée à l'aide du Concepteur Windows Form.  
    'Ne la modifiez pas à l'aide de l'éditeur de code.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.profileName = New System.Windows.Forms.ListBox()
        Me.score = New System.Windows.Forms.ListBox()
        Me.scoreTime = New System.Windows.Forms.ListBox()
        Me.totalTime = New System.Windows.Forms.ListBox()
        Me.totalPlays = New System.Windows.Forms.ListBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.playerCombo = New System.Windows.Forms.ComboBox()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Button2 = New System.Windows.Forms.Button()
        Me.checkbox_SortOrder = New System.Windows.Forms.CheckBox()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.SuspendLayout()
        '
        'profileName
        '
        Me.profileName.FormattingEnabled = True
        Me.profileName.Location = New System.Drawing.Point(28, 66)
        Me.profileName.Name = "profileName"
        Me.profileName.Size = New System.Drawing.Size(202, 173)
        Me.profileName.TabIndex = 0
        '
        'score
        '
        Me.score.FormattingEnabled = True
        Me.score.Location = New System.Drawing.Point(405, 64)
        Me.score.Name = "score"
        Me.score.Size = New System.Drawing.Size(59, 173)
        Me.score.TabIndex = 1
        '
        'scoreTime
        '
        Me.scoreTime.FormattingEnabled = True
        Me.scoreTime.Location = New System.Drawing.Point(469, 64)
        Me.scoreTime.Name = "scoreTime"
        Me.scoreTime.Size = New System.Drawing.Size(68, 173)
        Me.scoreTime.TabIndex = 2
        '
        'totalTime
        '
        Me.totalTime.FormattingEnabled = True
        Me.totalTime.Location = New System.Drawing.Point(251, 64)
        Me.totalTime.Name = "totalTime"
        Me.totalTime.Size = New System.Drawing.Size(69, 173)
        Me.totalTime.TabIndex = 4
        '
        'totalPlays
        '
        Me.totalPlays.FormattingEnabled = True
        Me.totalPlays.Location = New System.Drawing.Point(325, 64)
        Me.totalPlays.Name = "totalPlays"
        Me.totalPlays.Size = New System.Drawing.Size(61, 173)
        Me.totalPlays.TabIndex = 5
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(28, 47)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(44, 13)
        Me.Label1.TabIndex = 6
        Me.Label1.Text = "Joueurs"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label2.ForeColor = System.Drawing.SystemColors.InfoText
        Me.Label2.Location = New System.Drawing.Point(402, 45)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(67, 13)
        Me.Label2.TabIndex = 7
        Me.Label2.Text = "Score Max"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label3.ForeColor = System.Drawing.SystemColors.InfoText
        Me.Label3.Location = New System.Drawing.Point(467, 45)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(65, 13)
        Me.Label3.TabIndex = 8
        Me.Label3.Text = "Temps Lié"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(248, 45)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(70, 13)
        Me.Label4.TabIndex = 9
        Me.Label4.Text = "Cumul tps jeu"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(322, 45)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(64, 13)
        Me.Label5.TabIndex = 10
        Me.Label5.Text = "Nbre parties"
        '
        'playerCombo
        '
        Me.playerCombo.FormattingEnabled = True
        Me.playerCombo.Location = New System.Drawing.Point(28, 14)
        Me.playerCombo.Name = "playerCombo"
        Me.playerCombo.Size = New System.Drawing.Size(220, 21)
        Me.playerCombo.TabIndex = 11
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(254, 12)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(75, 23)
        Me.Button1.TabIndex = 12
        Me.Button1.Text = "Statistiques"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Button2
        '
        Me.Button2.Location = New System.Drawing.Point(436, 255)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(75, 23)
        Me.Button2.TabIndex = 13
        Me.Button2.Text = "Quitter"
        Me.Button2.UseVisualStyleBackColor = True
        '
        'checkbox_SortOrder
        '
        Me.checkbox_SortOrder.Appearance = System.Windows.Forms.Appearance.Button
        Me.checkbox_SortOrder.Font = New System.Drawing.Font("Microsoft Sans Serif", 11.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.checkbox_SortOrder.Location = New System.Drawing.Point(411, 11)
        Me.checkbox_SortOrder.Name = "checkbox_SortOrder"
        Me.checkbox_SortOrder.Size = New System.Drawing.Size(25, 24)
        Me.checkbox_SortOrder.TabIndex = 14
        Me.checkbox_SortOrder.Text = "▼"
        Me.checkbox_SortOrder.TextAlign = System.Drawing.ContentAlignment.TopLeft
        Me.checkbox_SortOrder.UseVisualStyleBackColor = True
        '
        'Label6
        '
        Me.Label6.BackColor = System.Drawing.SystemColors.ActiveCaption
        Me.Label6.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.Label6.Location = New System.Drawing.Point(407, 9)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(130, 28)
        Me.Label6.TabIndex = 15
        Me.Label6.Text = "Ordre de tri"
        Me.Label6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'form_scores
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(565, 289)
        Me.ControlBox = False
        Me.Controls.Add(Me.checkbox_SortOrder)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.playerCombo)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.totalPlays)
        Me.Controls.Add(Me.profileName)
        Me.Controls.Add(Me.totalTime)
        Me.Controls.Add(Me.scoreTime)
        Me.Controls.Add(Me.score)
        Me.Controls.Add(Me.Label6)
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "form_scores"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Tableau des scores"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents profileName As ListBox
    Friend WithEvents score As ListBox
    Friend WithEvents scoreTime As ListBox
    Friend WithEvents totalTime As ListBox
    Friend WithEvents totalPlays As ListBox
    Friend WithEvents Label1 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label4 As Label
    Friend WithEvents Label5 As Label
    Friend WithEvents playerCombo As ComboBox
    Friend WithEvents Button1 As Button
    Friend WithEvents Button2 As Button
    Friend WithEvents checkbox_SortOrder As CheckBox
    Friend WithEvents Label6 As Label
End Class
