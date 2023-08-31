<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class form_options
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
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Me.CheckBoxPause = New System.Windows.Forms.CheckBox()
        Me.CheckBoxCartesAleatoire = New System.Windows.Forms.CheckBox()
        Me.labelTimer = New System.Windows.Forms.Label()
        Me.ButtonIncrementTime = New System.Windows.Forms.Button()
        Me.ButtonDecrementTime = New System.Windows.Forms.Button()
        Me.buttonValidation = New System.Windows.Forms.Button()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.btn_abort = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'CheckBoxPause
        '
        Me.CheckBoxPause.Appearance = System.Windows.Forms.Appearance.Button
        Me.CheckBoxPause.BackColor = System.Drawing.SystemColors.Control
        Me.CheckBoxPause.FlatAppearance.BorderColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxPause.FlatAppearance.CheckedBackColor = System.Drawing.Color.MediumAquamarine
        Me.CheckBoxPause.FlatAppearance.MouseDownBackColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxPause.FlatAppearance.MouseOverBackColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxPause.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.CheckBoxPause.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.CheckBoxPause.ForeColor = System.Drawing.Color.Black
        Me.CheckBoxPause.Location = New System.Drawing.Point(56, 80)
        Me.CheckBoxPause.Margin = New System.Windows.Forms.Padding(2)
        Me.CheckBoxPause.Name = "CheckBoxPause"
        Me.CheckBoxPause.Size = New System.Drawing.Size(174, 31)
        Me.CheckBoxPause.TabIndex = 0
        Me.CheckBoxPause.Text = "pause non autorisée"
        Me.CheckBoxPause.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me.CheckBoxPause.UseVisualStyleBackColor = False
        '
        'CheckBoxCartesAleatoire
        '
        Me.CheckBoxCartesAleatoire.Appearance = System.Windows.Forms.Appearance.Button
        Me.CheckBoxCartesAleatoire.BackColor = System.Drawing.SystemColors.Control
        Me.CheckBoxCartesAleatoire.FlatAppearance.BorderColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxCartesAleatoire.FlatAppearance.CheckedBackColor = System.Drawing.Color.MediumAquamarine
        Me.CheckBoxCartesAleatoire.FlatAppearance.MouseDownBackColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxCartesAleatoire.FlatAppearance.MouseOverBackColor = System.Drawing.SystemColors.ButtonShadow
        Me.CheckBoxCartesAleatoire.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.CheckBoxCartesAleatoire.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.CheckBoxCartesAleatoire.ForeColor = System.Drawing.Color.Black
        Me.CheckBoxCartesAleatoire.Location = New System.Drawing.Point(56, 115)
        Me.CheckBoxCartesAleatoire.Margin = New System.Windows.Forms.Padding(2)
        Me.CheckBoxCartesAleatoire.Name = "CheckBoxCartesAleatoire"
        Me.CheckBoxCartesAleatoire.Size = New System.Drawing.Size(174, 32)
        Me.CheckBoxCartesAleatoire.TabIndex = 1
        Me.CheckBoxCartesAleatoire.Text = "cartes non triées"
        Me.CheckBoxCartesAleatoire.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me.CheckBoxCartesAleatoire.UseVisualStyleBackColor = False
        '
        'labelTimer
        '
        Me.labelTimer.BackColor = System.Drawing.Color.White
        Me.labelTimer.Location = New System.Drawing.Point(101, 177)
        Me.labelTimer.Margin = New System.Windows.Forms.Padding(2, 0, 2, 0)
        Me.labelTimer.Name = "labelTimer"
        Me.labelTimer.Size = New System.Drawing.Size(84, 41)
        Me.labelTimer.TabIndex = 3
        Me.labelTimer.Text = "<<aucune limite de temps>>"
        Me.labelTimer.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'ButtonIncrementTime
        '
        Me.ButtonIncrementTime.BackColor = System.Drawing.Color.MediumAquamarine
        Me.ButtonIncrementTime.Location = New System.Drawing.Point(189, 177)
        Me.ButtonIncrementTime.Margin = New System.Windows.Forms.Padding(2)
        Me.ButtonIncrementTime.Name = "ButtonIncrementTime"
        Me.ButtonIncrementTime.Size = New System.Drawing.Size(41, 41)
        Me.ButtonIncrementTime.TabIndex = 5
        Me.ButtonIncrementTime.Text = "+"
        Me.ButtonIncrementTime.UseVisualStyleBackColor = False
        '
        'ButtonDecrementTime
        '
        Me.ButtonDecrementTime.BackColor = System.Drawing.Color.NavajoWhite
        Me.ButtonDecrementTime.Location = New System.Drawing.Point(56, 177)
        Me.ButtonDecrementTime.Margin = New System.Windows.Forms.Padding(2)
        Me.ButtonDecrementTime.Name = "ButtonDecrementTime"
        Me.ButtonDecrementTime.Size = New System.Drawing.Size(41, 41)
        Me.ButtonDecrementTime.TabIndex = 6
        Me.ButtonDecrementTime.Text = "-"
        Me.ButtonDecrementTime.UseVisualStyleBackColor = False
        '
        'buttonValidation
        '
        Me.buttonValidation.AutoSize = True
        Me.buttonValidation.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!)
        Me.buttonValidation.Location = New System.Drawing.Point(115, 251)
        Me.buttonValidation.Margin = New System.Windows.Forms.Padding(2)
        Me.buttonValidation.Name = "buttonValidation"
        Me.buttonValidation.Size = New System.Drawing.Size(78, 25)
        Me.buttonValidation.TabIndex = 7
        Me.buttonValidation.Text = "Enregistrer"
        Me.buttonValidation.UseVisualStyleBackColor = True
        '
        'Label1
        '
        Me.Label1.BackColor = System.Drawing.Color.White
        Me.Label1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.Label1.Font = New System.Drawing.Font("Impact", 14.25!, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label1.ForeColor = System.Drawing.Color.Black
        Me.Label1.Location = New System.Drawing.Point(12, 14)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(262, 224)
        Me.Label1.TabIndex = 8
        Me.Label1.Text = "Règlage de la difficulté"
        '
        'Label2
        '
        Me.Label2.BackColor = System.Drawing.Color.White
        Me.Label2.Location = New System.Drawing.Point(23, 43)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(236, 30)
        Me.Label2.TabIndex = 9
        Me.Label2.Text = "La couleur verte indique que la partie sera très facile"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.White
        Me.Label3.Location = New System.Drawing.Point(53, 162)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(76, 13)
        Me.Label3.TabIndex = 10
        Me.Label3.Text = "Temps alloué :"
        '
        'btn_abort
        '
        Me.btn_abort.AutoSize = True
        Me.btn_abort.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_abort.Location = New System.Drawing.Point(197, 251)
        Me.btn_abort.Margin = New System.Windows.Forms.Padding(2)
        Me.btn_abort.Name = "btn_abort"
        Me.btn_abort.Size = New System.Drawing.Size(78, 25)
        Me.btn_abort.TabIndex = 11
        Me.btn_abort.Text = "Annuler"
        Me.btn_abort.UseVisualStyleBackColor = True
        '
        'form_options
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(289, 287)
        Me.ControlBox = False
        Me.Controls.Add(Me.btn_abort)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.buttonValidation)
        Me.Controls.Add(Me.ButtonDecrementTime)
        Me.Controls.Add(Me.ButtonIncrementTime)
        Me.Controls.Add(Me.labelTimer)
        Me.Controls.Add(Me.CheckBoxCartesAleatoire)
        Me.Controls.Add(Me.CheckBoxPause)
        Me.Controls.Add(Me.Label1)
        Me.Margin = New System.Windows.Forms.Padding(2)
        Me.Name = "form_options"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Options du jeu"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents CheckBoxPause As CheckBox
    Friend WithEvents CheckBoxCartesAleatoire As CheckBox
    Friend WithEvents labelTimer As Label
    Friend WithEvents ButtonIncrementTime As Button
    Friend WithEvents ButtonDecrementTime As Button
    Friend WithEvents buttonValidation As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents btn_abort As Button
End Class
