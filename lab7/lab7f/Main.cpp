//
// Created by purple on 24-12-12.
//
#include <iostream>
using namespace std;
struct node {
    int val;
    int height;
    int count;
    node *right;
    node *left;
    explicit node (int val):val(val),right(nullptr),left(nullptr),count(0),height(1){}

};
class AVL_Tree {
    node *root_ ;
    int factor=1;
    node* left_rotate(node* fix) {
        node* temp = fix->right;
        node* temp2 = fix->left;
        fix->right=temp2;
        temp->left=fix;
        update_height(fix);
        update_height(temp);
        return temp;
    }
    node* right_rotate(node* fix) {
        node* temp = fix->left;
        node* temp2 = fix->right;
        fix->left=temp2;
        temp->right=fix;
        update_height(fix);
        update_height(temp);
        return temp;
    }
    int get_height(node *node) {
        return node?node->height:0;
    }
    void update_height(node* check) {
        if(check) {
            check->height=max(get_height(check->left),get_height(check->right)) + 1;
        }
    }
    int get_balance_factor(node* node) {
        return node?get_height(node->left) - get_height(node->right):0;
    }
    node* balance(node* node) {
        if(!node)return nullptr;
        update_height(node);
        int factor=get_balance_factor(node);
        if(factor>1) {
            if(get_balance_factor(node->left)<0) {
                node->left=left_rotate(node->left);
            }
            return right_rotate(node);
        }
        if(factor<-1) {
            if(get_balance_factor(node->right)>0) {
                node->right=right_rotate(node->right);
            }
            return left_rotate(node);
        }
        return node;
    }
public:
    AVL_Tree():root_(nullptr){}
    ~AVL_Tree() = default;

    node* add (node* current ,int val) {
        if(!current) {
            return new node(val);
        }
        if(val<current->val) {
            current->left = add(current->left,val);
        }
        else if(val>current->val) {
            current->right = add(current->right,val);
        }
        else {
            current->count++;
            return current;
        }
        return balance(current);
    }
    node* getMinNode(node* node) {
        while (node->left) {
            node = node->left;
        }
        return node;
    }
    node* remove(node* root, int val) {
        if (!root) return nullptr;

        if (val < root->val) {
            root->left = remove(root->left, val);
        } else if (val > root->val) {
            root->right = remove(root->right, val);
        } else {
            // 找到目标节点
            if(root->count >0) {
                root->count--;
            }else{
                if (!root->left || !root->right) {
                    node* temp = root->left ? root->left : root->right;
                    //delete root;
                    return temp;
                } else {
                    node* minNode = getMinNode(root->right);
                    root->val = minNode->val;
                    root->right = remove(root->right, minNode->val);
                }
            }
        }
        return balance(root);
    }
    int search(node* &root,int val) {
        node* current = root;
        int difference=INT32_MAX;
        int be_remove=0;
        while(current) {
            if(current->val==val) {
                be_remove=val;
                difference=0;
                break;
            }else if(val<current->val) {
                int temp_difference=abs(current->val-val);
                if(temp_difference<difference) {
                    difference=temp_difference;
                    be_remove=current->val;
                }
                current = current->left;
            }
            else{
                int temp_difference=abs(current->val-val);
                if(temp_difference<difference) {
                    difference=temp_difference;
                    be_remove=current->val;
                }
                current = current->right;
            }
        }
        root=remove(root,be_remove);
        return difference;
    }
};
int main() {
    int n=0;
    scanf("%d",&n);
    AVL_Tree pets;
    AVL_Tree people;
    int pets_size=0;
    int people_size=0;
    int sum=0;
    node* root_pats = nullptr;
    node* root_people = nullptr;
    for(int i=0;i<n;i++) {
        int judge=0;
        int val=0;
        scanf("%d %d",&judge,&val);
        if(judge==0) {
            if(people_size) {
                sum+=people.search(root_people,val);
                people_size--;
            }else {
                root_pats=pets.add(root_pats,val);
                pets_size++;
            }
        }else {
            if(pets_size) {
                sum+=pets.search(root_pats,val);
                pets_size--;
            }else {
                root_people=people.add(root_people,val);
                people_size++;
            }
        }
    }
    printf("%d",sum);
    return 0;
}