<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class form_home
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
        Me.btn_play = New System.Windows.Forms.Button()
        Me.ComboBox_name = New System.Windows.Forms.ComboBox()
        Me.lbl_constTitle = New System.Windows.Forms.Label()
        Me.lbl_constDescr = New System.Windows.Forms.Label()
        Me.lbl_constEnterName = New System.Windows.Forms.Label()
        Me.lbl_nameValid = New System.Windows.Forms.Label()
        Me.btn_option = New System.Windows.Forms.Button()
        Me.btn_score = New System.Windows.Forms.Button()
        Me.btn_exit = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'btn_play
        '
        Me.btn_play.Enabled = False
        Me.btn_play.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_play.Location = New System.Drawing.Point(31, 91)
        Me.btn_play.Name = "btn_play"
        Me.btn_play.Size = New System.Drawing.Size(167, 35)
        Me.btn_play.TabIndex = 0
        Me.btn_play.Text = "Jouer"
        Me.btn_play.UseVisualStyleBackColor = True
        '
        'ComboBox_name
        '
        Me.ComboBox_name.FormattingEnabled = True
        Me.ComboBox_name.Location = New System.Drawing.Point(31, 64)
        Me.ComboBox_name.Name = "ComboBox_name"
        Me.ComboBox_name.Size = New System.Drawing.Size(167, 21)
        Me.ComboBox_name.TabIndex = 1
        '
        'lbl_constTitle
        '
        Me.lbl_constTitle.AutoSize = True
        Me.lbl_constTitle.Font = New System.Drawing.Font("Impact", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lbl_constTitle.Location = New System.Drawing.Point(242, 9)
        Me.lbl_constTitle.Name = "lbl_constTitle"
        Me.lbl_constTitle.Size = New System.Drawing.Size(152, 25)
        Me.lbl_constTitle.TabIndex = 2
        Me.lbl_constTitle.Text = "HardcoreMemory"
        '
        'lbl_constDescr
        '
        Me.lbl_constDescr.AutoSize = True
        Me.lbl_constDescr.Location = New System.Drawing.Point(244, 34)
        Me.lbl_constDescr.Name = "lbl_constDescr"
        Me.lbl_constDescr.Size = New System.Drawing.Size(146, 13)
        Me.lbl_constDescr.TabIndex = 3
        Me.lbl_constDescr.Text = "Un jeu interdit aux mauviettes"
        '
        'lbl_constEnterName
        '
        Me.lbl_constEnterName.AutoSize = True
        Me.lbl_constEnterName.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lbl_constEnterName.Location = New System.Drawing.Point(27, 15)
        Me.lbl_constEnterName.Name = "lbl_constEnterName"
        Me.lbl_constEnterName.Size = New System.Drawing.Size(153, 20)
        Me.lbl_constEnterName.TabIndex = 4
        Me.lbl_constEnterName.Text = "Sélection du joueur :"
        '
        'lbl_nameValid
        '
        Me.lbl_nameValid.Font = New System.Drawing.Font("Microsoft Sans Serif", 7.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lbl_nameValid.Location = New System.Drawing.Point(41, 35)
        Me.lbl_nameValid.Name = "lbl_nameValid"
        Me.lbl_nameValid.Size = New System.Drawing.Size(157, 26)
        Me.lbl_nameValid.TabIndex = 5
        Me.lbl_nameValid.Text = "Vous pouvez choisir un joueur existant ou créer un nouveau"
        '
        'btn_option
        '
        Me.btn_option.Location = New System.Drawing.Point(315, 63)
        Me.btn_option.Name = "btn_option"
        Me.btn_option.Size = New System.Drawing.Size(75, 23)
        Me.btn_option.TabIndex = 6
        Me.btn_option.Text = "Options"
        Me.btn_option.UseVisualStyleBackColor = True
        '
        'btn_score
        '
        Me.btn_score.Location = New System.Drawing.Point(315, 92)
        Me.btn_score.Name = "btn_score"
        Me.btn_score.Size = New System.Drawing.Size(75, 23)
        Me.btn_score.TabIndex = 7
        Me.btn_score.Text = "Scores"
        Me.btn_score.UseVisualStyleBackColor = True
        '
        'btn_exit
        '
        Me.btn_exit.Location = New System.Drawing.Point(315, 121)
        Me.btn_exit.Name = "btn_exit"
        Me.btn_exit.Size = New System.Drawing.Size(75, 23)
        Me.btn_exit.TabIndex = 8
        Me.btn_exit.Text = "Quitter"
        Me.btn_exit.UseVisualStyleBackColor = True
        '
        'form_home
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(406, 152)
        Me.Controls.Add(Me.btn_exit)
        Me.Controls.Add(Me.btn_score)
        Me.Controls.Add(Me.btn_option)
        Me.Controls.Add(Me.lbl_nameValid)
        Me.Controls.Add(Me.lbl_constEnterName)
        Me.Controls.Add(Me.lbl_constDescr)
        Me.Controls.Add(Me.lbl_constTitle)
        Me.Controls.Add(Me.ComboBox_name)
        Me.Controls.Add(Me.btn_play)
        Me.MinimumSize = New System.Drawing.Size(422, 190)
        Me.Name = "form_home"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Hardcore Memory"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents btn_play As Button
    Friend WithEvents ComboBox_name As ComboBox
    Friend WithEvents lbl_constTitle As Label
    Friend WithEvents lbl_constDescr As Label
    Friend WithEvents lbl_constEnterName As Label
    Friend WithEvents lbl_nameValid As Label
    Friend WithEvents btn_option As Button
    Friend WithEvents btn_score As Button
    Friend WithEvents btn_exit As Button
End Class
