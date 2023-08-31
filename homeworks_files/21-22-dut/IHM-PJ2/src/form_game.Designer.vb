<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class form_game
    Inherits System.Windows.Forms.Form

    'Form remplace la méthode Dispose pour nettoyer la liste des composants.
    <System.Diagnostics.DebuggerNonUserCode()>
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
        Me.quitBtn = New System.Windows.Forms.Button()
        Me.cards_container = New System.Windows.Forms.FlowLayoutPanel()
        Me.zaWarudo_btn = New System.Windows.Forms.Button()
        Me.lbl_constProfile = New System.Windows.Forms.Label()
        Me.lbl_profile = New System.Windows.Forms.Label()
        Me.lbl_time = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.pauseIndicator_lbl = New System.Windows.Forms.Label()
        Me.SuspendLayout()
        '
        'quitBtn
        '
        Me.quitBtn.Location = New System.Drawing.Point(287, 12)
        Me.quitBtn.Name = "quitBtn"
        Me.quitBtn.Size = New System.Drawing.Size(75, 23)
        Me.quitBtn.TabIndex = 1
        Me.quitBtn.Text = "Abandon"
        Me.quitBtn.UseVisualStyleBackColor = True
        '
        'cards_container
        '
        Me.cards_container.Location = New System.Drawing.Point(29, 47)
        Me.cards_container.Name = "cards_container"
        Me.cards_container.Size = New System.Drawing.Size(515, 500)
        Me.cards_container.TabIndex = 4
        '
        'zaWarudo_btn
        '
        Me.zaWarudo_btn.Location = New System.Drawing.Point(206, 11)
        Me.zaWarudo_btn.Name = "zaWarudo_btn"
        Me.zaWarudo_btn.Size = New System.Drawing.Size(75, 24)
        Me.zaWarudo_btn.TabIndex = 5
        Me.zaWarudo_btn.Text = "Pause"
        Me.zaWarudo_btn.UseVisualStyleBackColor = True
        Me.zaWarudo_btn.Visible = False
        '
        'lbl_constProfile
        '
        Me.lbl_constProfile.AutoSize = True
        Me.lbl_constProfile.Location = New System.Drawing.Point(26, 17)
        Me.lbl_constProfile.Name = "lbl_constProfile"
        Me.lbl_constProfile.Size = New System.Drawing.Size(45, 13)
        Me.lbl_constProfile.TabIndex = 6
        Me.lbl_constProfile.Text = "Joueur :"
        '
        'lbl_profile
        '
        Me.lbl_profile.AutoSize = True
        Me.lbl_profile.Location = New System.Drawing.Point(77, 17)
        Me.lbl_profile.Name = "lbl_profile"
        Me.lbl_profile.Size = New System.Drawing.Size(79, 13)
        Me.lbl_profile.TabIndex = 7
        Me.lbl_profile.Text = "<nom du profil>"
        '
        'lbl_time
        '
        Me.lbl_time.AutoSize = True
        Me.lbl_time.Location = New System.Drawing.Point(497, 17)
        Me.lbl_time.Name = "lbl_time"
        Me.lbl_time.Size = New System.Drawing.Size(48, 13)
        Me.lbl_time.TabIndex = 9
        Me.lbl_time.Text = "<mm:ss>"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(408, 17)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(83, 13)
        Me.Label2.TabIndex = 8
        Me.Label2.Text = "Temps restant : "
        '
        'pauseIndicator_lbl
        '
        Me.pauseIndicator_lbl.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.pauseIndicator_lbl.Font = New System.Drawing.Font("Impact", 26.25!, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.pauseIndicator_lbl.ForeColor = System.Drawing.Color.White
        Me.pauseIndicator_lbl.Location = New System.Drawing.Point(12, 233)
        Me.pauseIndicator_lbl.Name = "pauseIndicator_lbl"
        Me.pauseIndicator_lbl.Size = New System.Drawing.Size(533, 95)
        Me.pauseIndicator_lbl.TabIndex = 10
        Me.pauseIndicator_lbl.Text = "Jeu en pause"
        Me.pauseIndicator_lbl.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me.pauseIndicator_lbl.Visible = False
        '
        'form_game
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(557, 559)
        Me.Controls.Add(Me.pauseIndicator_lbl)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.lbl_time)
        Me.Controls.Add(Me.lbl_profile)
        Me.Controls.Add(Me.lbl_constProfile)
        Me.Controls.Add(Me.zaWarudo_btn)
        Me.Controls.Add(Me.cards_container)
        Me.Controls.Add(Me.quitBtn)
        Me.Name = "form_game"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Hardcore Memory (en train de jouer)"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents quitBtn As Button
    Friend WithEvents cards_container As FlowLayoutPanel
    Friend WithEvents zaWarudo_btn As Button
    Friend WithEvents lbl_constProfile As Label
    Friend WithEvents lbl_profile As Label
    Friend WithEvents lbl_time As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents pauseIndicator_lbl As Label
End Class
