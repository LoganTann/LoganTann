Imports System.IO
Module GameUtils
    ''' <summary>
    ''' Crée une nouvelle MsgBox de type Yes/No et retourne true si yes a été cliqué, false sinon.
    ''' Conçu pour agir comme le confirm("message") de Javascript
    ''' </summary>
    ''' <param name="message">Le message que doit contenir la MsgBox</param>
    ''' <returns>true si le bouton "oui" a été cliqué, false sinon</returns>
    Public Function confirm(message As String) As Boolean
        Return MsgBox(message, MsgBoxStyle.YesNo) = MsgBoxResult.Yes
    End Function


    ''' <summary>fonction statique transformant un chemin relatif au dossier de projet en chemin absolu. Doit être démarré sur VS2019</summary>
    ''' <param name="filePath">Le chemin relatif au dossier du projet en format windows</param>
    ''' <returns>littéralement Directory.GetCurrentDirectory().Replace("\bin\Debug", "\").Replace("\bin\Release", "\") + filePath</returns>
    Public Function getFile(filePath As String, replace As Boolean) As String
        Dim rawCD As String = Directory.GetCurrentDirectory()
        If (replace) Then
            Dim newCD = rawCD.Replace("\bin\Debug", "\").Replace("\bin\Release", "\")
            Return newCD + filePath
        Else
            Return rawCD + "\" + filePath
        End If
    End Function

    Public Function CD() As String
        Return Directory.GetCurrentDirectory()
    End Function

    Public Function generateListOfIndex(Count As Integer) As List(Of Integer)
        Dim indexList As New List(Of Integer)
        For index As Integer = 0 To Count - 1
            indexList.Add(index)
        Next
        Return indexList
    End Function

    ''' <summary>
    ''' Donné une valeur en secondes, convertis en minutes/secondes selon le template mis en paramètre.
    ''' Ex : donné 90 secs et la chaine "mm minutes et ss secondes", retourne "1 minutes et 30 secondes" 
    ''' </summary>
    ''' <param name="time">Le temps en secondes. Cette fonction est conçue pour un maximum de 3599 secondes (59 minutes 59 secs)</param>
    ''' <param name="template">Une chaine de caractères où l'occurence "mm" sera remplacée par la valeur des minutes, et "ss" sera remplacée par la valeur des secondes</param>
    ''' <returns>(time, template) => template.Replace("ss", time Mod 60).Replace("mm",  (time - (time Mod 60)) / 60)</returns>
    Public Function secsToStr(time As Integer, template As String) As String
        Dim ss As Integer = time Mod 60
        Dim mm As Integer = (time - ss) / 60
        Return template.Replace("ss", ss.ToString("D2")).Replace("mm", mm.ToString("D2"))
    End Function
End Module
